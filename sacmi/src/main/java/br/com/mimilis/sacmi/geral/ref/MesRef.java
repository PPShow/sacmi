package br.com.mimilis.sacmi.geral.ref;

import java.util.Calendar;

public enum MesRef {

	 JAN (1,	Calendar.JANUARY,	"Janeiro",	"Jan")
	,FEV (2,	Calendar.FEBRUARY,	"Fevereiro","Fev")
	,MAR (3,	Calendar.MARCH,		"Março",	"Mar")
	,ABR (4,	Calendar.APRIL,		"Abril",	"Abr")
	,MAI (5,	Calendar.MAY,		"Maio",		"Mai")
	,JUN (6,	Calendar.JUNE,		"Junho",	"Jun")
	,JUL (7,	Calendar.JULY,		"Julho",	"Jul")
	,AGO (8,	Calendar.AUGUST,	"Agosto",	"Ago")
	,SET (9,	Calendar.SEPTEMBER,	"Setembro",	"Set")
	,OUT (10,	Calendar.OCTOBER,	"Outubro",	"Out")
	,NOV (11,	Calendar.NOVEMBER,	"Novembro",	"Nov")
	,DEZ (12,	Calendar.DECEMBER,	"Dezembro",	"Dez")
	;
	
	public Integer id;
	public Integer calendarId;
	public String nomeCompleto;
	public String abrev;
	
	private MesRef(Integer id, Integer calendarId, String nomeCompleto, String abrev) {
		this.id = id;
		this.calendarId = calendarId;
		this.nomeCompleto = nomeCompleto;
		this.abrev = abrev;
	}
	
	public static MesRef matchAnyString(String value) {
		if(value == null) {
			return null;
		}
		
		for(MesRef mr : MesRef.values()) {
			if( mr.abrev.toUpperCase().equals(value.trim().toUpperCase())
					|| mr.nomeCompleto.toUpperCase().equals(value.trim().toUpperCase()) 
				) {
				return mr;
			}
		}
		
		return null;
	}
	
	public static MesRef matchValue(Integer value, boolean isValueZeroBased) {
		if(value != null && value > -1 && value < 13) {
			for(MesRef mr : MesRef.values()) {
				if( (isValueZeroBased && mr.calendarId.equals(value)) || (!isValueZeroBased && mr.id.equals(value)) ) {
					return mr;
				}
			}
		}
		return null;
	}

}
