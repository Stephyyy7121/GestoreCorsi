package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;
import it.polito.tdp.corsi.db.StudenteDAO;

public class Model {
	
	//Classe che permette di passare dal DB all'interfaccia grafica
	// --> il modello parla con il controller
	
	//PUNTO 1 e 2
	private CorsoDAO corsoDAO;
	
	private StudenteDAO studenteDAO;
	
	public Model() {
		this.corsoDAO = new CorsoDAO();
		this.studenteDAO = new StudenteDAO();
	}
	
	//CONSEGNA 1
	//METODO CHE RESTITUISCE UNA LISTA DI CORSI che rispettano condizione desiderata del primo punot dell'esercizio
	public List<Corso> getCorsoByPeriodo(int  periodo) {
		return this.corsoDAO.getCorsoPeriodo(periodo);
	    //metodo che CHIEDE AL dao DI INTERROGARE IL DB 
	}
	
	//CONSEGNA 2: STAMPARE IL NUMERO DI ISCRITTI AI CORSI DI UN DETERMINATO PERIODO DIDATTICO
	public Map<Corso, Integer> getCorsiIscritti(int periodo) {
		
		return this.corsoDAO.getCorsiIscritti(periodo);
	}
	
	
	//CONSEGNA 3: ELENCARE TUTTI GLI STUDENTI APPARTENENTI AD UN CERTO CORSO
	//dopo richiamare il model nel controller
	public List<Studente> getIscrittiCorso(String codins) {
		
		return this.studenteDAO.getIscrittiCorso(codins);
	}
	
	//CONSEGNA 4 : DATO CORSO, RESTITUIRE LA SUDDIVIONE DEGLI STUDENTI IN BASE AL CDS
	
	public List<Divisione> getDivisioneStudentiCorso(String codins) {
		return this.studenteDAO.divisioneStudentiCorso(codins);
	}
}
