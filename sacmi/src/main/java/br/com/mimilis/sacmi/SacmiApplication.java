package br.com.mimilis.sacmi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.mimilis.sacmi.fin.domain.LivroRazao;
import br.com.mimilis.sacmi.fin.repositories.LivroRazaoRepository;
import br.com.mimilis.sacmi.fin.tools.ImportarExcel;

@SpringBootApplication
public class SacmiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SacmiApplication.class, args);
	}
	
	@Autowired
	private LivroRazaoRepository lrDao;
	
	@Override
	public void run(String... arg0) throws Exception {
		
		ImportarExcel imp = new ImportarExcel("C:\\Users\\Pedro\\Desktop\\Programação\\Spring Boot\\Contas Mi Milis.xlsx");
//		LivroRazao abr = imp.importarDataSet(4, 2016);
//		LivroRazao mai = imp.importarDataSet(5, 2016);
//		LivroRazao jun = imp.importarDataSet(6, 2016);
//		lrDao.save(Arrays.asList(abr,mai,jun) );
		
		List<LivroRazao> lrs = imp.importarDataSet(2016, 4, 2017, 11);
		lrDao.save(lrs);
	}
}
