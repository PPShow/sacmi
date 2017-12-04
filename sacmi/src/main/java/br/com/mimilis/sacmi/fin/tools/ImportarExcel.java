package br.com.mimilis.sacmi.fin.tools;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import br.com.mimilis.sacmi.fin.domain.Despesa;
import br.com.mimilis.sacmi.fin.domain.DespesaItem;
import br.com.mimilis.sacmi.fin.domain.LivroRazao;
import br.com.mimilis.sacmi.fin.domain.MovimentoFinanceiro;
import br.com.mimilis.sacmi.fin.domain.Receita;
import br.com.mimilis.sacmi.fin.domain.ReceitaItem;
import br.com.mimilis.sacmi.fin.exception.ExcelToolsException;
import br.com.mimilis.sacmi.fin.ref.TipoItemFinanceiro;
import br.com.mimilis.sacmi.geral.ref.MesRef;

public class ImportarExcel implements Serializable{
	
	private static final String ERR_MES_VAZIO = "Nenhum mês informado";
	private static final String ERR_ANO_VAZIO = "Nenhum ano informado";
	private static final String ERR_MES_INVALIDO = "Mês informado é inválido";
	private static final String ERR_PATTERN_NOT_FOUND = "Nenhuma planilha encontrada com os padrões de Mês/Ano passados";
	private static final String ERR_OPERACAO_NAO_REALIZADA = "O sistema não encontrou planilhas válidas para importação";
	
	private static final String ERR_REF_VALUES_NOT_FOUND = "Valores de referência não encontrados";
	
	private static final String ERR_CELL_CONTENT_EMPTY = "Conteúdo da célula está vazio";
	
	private static final long serialVersionUID = 1L;
	
	private String filePath;
	
	public ImportarExcel(String filePath) {
		this.filePath = filePath;
	}
	
	public List<LivroRazao> importarDataSet(Integer anoIni, Integer mesIni, Integer anoFim, Integer mesFim) throws Exception {
		ExcelReaderTools ert = new ExcelReaderTools(filePath);
		ert.openWorkbook();
		
		Exception failed=null;
		
		List<LivroRazao> livros = new ArrayList<LivroRazao>();
		
		if(!validateNonNegativeValues(anoIni, mesIni, anoFim, mesFim)) {
			throw new ExcelToolsException("Um dos parâmetros passados é negativo ou nulo: "+anoIni+"-"+mesIni+", "+anoFim+"-"+mesFim);
		}
		
		if(mesFim.intValue() == 12) {
			mesFim = 1;
			anoFim ++;
		}
		else {
			mesFim++;
		}
		
		try {
			
			for(Integer mes=mesIni, ano=anoIni; mes==mesFim && ano==anoFim;) {
				
				livros.add(importarDataSet(mes, ano, ert));
				
				if(mes.intValue() == 12) {
					mes = 1;
					ano ++;
				}
				else {
					mes++;
				}
			}
			
		} catch (Exception ex) {
			failed = ex;
		} 
		
		ert.closeWorkbook();
		
		if(failed != null) {
			throw failed;
		}
		
		return livros;
	}
	
	protected LivroRazao importarDataSet(Integer mes, Integer ano, ExcelReaderTools ert) throws Exception {
		
		if(mes == null || ano == null) {
			throw new ExcelToolsException(mes == null ? ERR_MES_VAZIO : ERR_ANO_VAZIO);
		}
		
		Sheet chosen = null;
		MesRef mr = MesRef.matchValue(mes, false);
		if(mr == null) {
			throw new ExcelToolsException(ERR_MES_INVALIDO);
		}
		
		String sheetName;
		for(Sheet sheet : ert.getWorkbook()) {
			sheetName = sheet.getSheetName().toUpperCase();
			if( (sheetName.contains(mr.abrev.toUpperCase()) || sheetName.contains(mr.nomeCompleto.toUpperCase())) && sheetName.contains(ano.toString()) ) {
				chosen = sheet;
				break;
			}
		}
		
		if(chosen == null) {
			throw new ExcelToolsException(ERR_PATTERN_NOT_FOUND);
		}
		
		if( (ano.intValue() == 2016 && mes > 3) || ano.intValue() == 2017) {
			return importDataSet_NovaPlanilha(chosen, mes, ano);
		}
		
		throw new ExcelToolsException(ERR_OPERACAO_NAO_REALIZADA);
		//return null;
	}
	
	//METODOS DE LEITURA ESPECIFICOS
	protected LivroRazao importDataSet_NovaPlanilha(Sheet sheet, Integer mes, Integer ano) throws Exception {
		
		if(sheet != null && mes != null && ano != null) {
			
			String H_DESCRICAO = "Descrição";
			String H_DATA = "Data";
			String H_R_DINHEIRO = "Dinheiro";
			String H_R_DEBITO = "Débito c/2,39% taxa";
			String H_R_CREDITO = "Crédito à vista C/ 3,19% taxa";
			String H_DESPESA = "Saída";
			
			String H_STOP = "TOTAL";
			
			int rowNumStart = -1;
			int headDescricao = -1;
			int headData = -1;
			int headRecDinheiro = -1;
			int headRecDebito = -1;
			int headRecCredito = -1;
			int headDespesa = -1;
			
			boolean isMapped = false;
			
			String content;
			int colInd;
			DataFormatter formatter = new DataFormatter();
			
			//MAPEAMENTO
			for(Row row : sheet) {
				
				for(Cell cell : row) {
					
					content = formatter.formatCellValue(cell);
					colInd = cell.getColumnIndex();
					
					if(matchHeaderContent(H_DESCRICAO, content)) {
						headDescricao = colInd;
					}
					if(matchHeaderContent(H_DATA, content)) {
						headData = colInd;
					}
					if(matchHeaderContent(H_R_DINHEIRO, content)) {
						headRecDinheiro = colInd;
					}
					if(matchHeaderContent(H_R_DEBITO, content)) {
						headRecDebito = colInd;
					}
					if(matchHeaderContent(H_R_CREDITO, content)) {
						headRecCredito = colInd;
					}
					if(matchHeaderContent(H_DESPESA, content)) {
						headDespesa = colInd;
					}
				}
				
				if(validateNonNegativeValues(headDescricao, headData, headRecDinheiro, headRecDebito, headRecCredito, headDespesa)) {
					rowNumStart = row.getRowNum()+1;
					isMapped = true;
					break;
				}
			}
			
			if(!isMapped) {
				throw new ExcelToolsException(ERR_REF_VALUES_NOT_FOUND);
			}
			
			LivroRazao lr = new LivroRazao(null, mes, ano);
			Map<Calendar, Receita> mapReceita = new HashMap<Calendar,Receita>();
			Map<Calendar, Despesa> mapDespesa = new HashMap<Calendar,Despesa>();
			Receita rec;
			Despesa des;
			
			//LEITURA
			Row row;
			boolean isStop = false;
			String descricao;
			Calendar data;
			Double recDin;
			Double recDeb;
			Double recCre;
			Double desp;
			for(Integer rowNum = rowNumStart; rowNum <= sheet.getLastRowNum(); rowNum++) {
				
				descricao = null;
				data = null;
				recDin = null;
				recDeb = null;
				recCre = null;
				desp = null;
				row = sheet.getRow(rowNum);
				
				for(Cell cell : row) {
					
					colInd = cell.getColumnIndex();
					//DESCRICAO
					if(colInd == headDescricao) {
						descricao = cell.getStringCellValue();
						if(matchHeaderContent(descricao, H_STOP)) {
							isStop = true;
							break;
						}
					}
					//DATA
					else if(colInd == headData) {
						
						String conteudoData = formatter.formatCellValue(cell);
						if(cell == null || conteudoData == null || conteudoData.isEmpty() ) {
							isStop = true;
							break;
						}
						
						data = Calendar.getInstance();
						
						if(CellType.STRING.equals(cell.getCellTypeEnum()) ) {
							data.setTime( convertFromUndeterminedFormat( conteudoData ) );
						}
						else {
							data.setTime( cell.getDateCellValue() );
						}
					}
					//RECEITA DINHEIRO/DEBITO
					else if(colInd == headRecDinheiro) {
						recDin = cell.getNumericCellValue();
					}
					//RECEITA DEBITO
					else if(colInd == headRecDebito) {
						recDeb = cell.getNumericCellValue();
					}
					//RECEITA CREDITO
					else if(colInd == headRecCredito) {
						recCre = cell.getNumericCellValue();
					}
					//DESPESA
					else if(colInd == headDespesa) {
						desp = cell.getNumericCellValue();
					}
				}
				
				if(isStop) {
					break;
				}
				
				//CONFIGURACAO RECEITA
				if( validateAnyPositive(recDin, recDeb, recCre) ) {
					rec = mapReceita.get(data);
					if(rec == null) {
						rec = new Receita(null, descricao, data);
					}
					if(recDin != null && recDin > 0.0) {
						rec.getItens().add( new ReceitaItem(null, rec, TipoItemFinanceiro.REC_DINHEIRO, recDin) );
						rec.addValor(recDin);
					}
					if(recDeb != null && recDeb > 0.0) {
						rec.getItens().add( new ReceitaItem(null, rec, TipoItemFinanceiro.REC_CARTAO_DEBITO, recDeb) );
						rec.addValor(recDeb);
					}
					if(recCre != null && recCre > 0.0) {
						rec.getItens().add( new ReceitaItem(null, rec, TipoItemFinanceiro.REC_CARTAO_CREDITO, recCre) );
						rec.addValor(recCre);
					}
					mapReceita.put(data, rec);
				}
				
				//CONFIGURACAO DESPESA
				if(desp != null && desp > 0.0) {
					des = mapDespesa.get(data);
					if(des == null) {
						des = new Despesa(null,descricao,data);
					}
					des.getItens().add(new DespesaItem(null, des, TipoItemFinanceiro.DESP_GERAL, desp));
					des.addValor(desp);
					mapDespesa.put(data, des);
				}
			}
			
			//CRIACAO DO LIVRO RAZAO
			MovimentoFinanceiro receita;
			for(Calendar key : mapReceita.keySet()) {
				receita = mapReceita.get(key);
				receita.setLivroRazao(lr);
				lr.getReceitas().add((Receita) receita);
				lr.addValor(receita.getValor());
			}
			
			Despesa despesa;
			for(Calendar key : mapDespesa.keySet()) {
				despesa = mapDespesa.get(key);
				despesa.setLivroRazao(lr);
				lr.getDespesas().add((Despesa) despesa);
				lr.subtrValor(despesa.getValor());
			}
			
			return lr;
		}
		
		return null;
	}
	
	private Date convertFromUndeterminedFormat(String cellContent) throws Exception {
		if(cellContent != null) {
			SimpleDateFormat sdfSimple = new SimpleDateFormat("d/M/yy");
			SimpleDateFormat sdfMinor = new SimpleDateFormat("dd/MM/yy");
			SimpleDateFormat sdfComplete = new SimpleDateFormat("dd/MM/yyyy");
			
			cellContent = cellContent.trim();
			if(cellContent.length() == 6) {
				return sdfSimple.parse(cellContent);
			}
			else if(cellContent.length() == 8) {
				return sdfMinor.parse(cellContent);
			}
			else if(cellContent.length() == 10) {
				return sdfComplete.parse(cellContent);
			}
		}
		
		throw new ExcelToolsException(ERR_CELL_CONTENT_EMPTY);
	}
	
	//MATCHERS & VALIDATORS
	private boolean matchHeaderContent(String header, String content) {
		if( header != null && !header.isEmpty() && content != null && !content.isEmpty() ) {
			return header.trim().toUpperCase().equals( content.trim().toUpperCase() );
		}
		return false;
	}
	
	private boolean validateNonNegativeValues(Integer...values) {
		if(values != null && values.length > 0) {
			for(Integer value : values) {
				if(value == null || value < 0) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	private boolean validateAnyPositive(Double ...values) {
		if(values != null && values.length > 0) {
			for(Double value : values) {
				if(value != null && value > 0.0) {
					return true;
				}
			}
		}
		return false;
	}
}
