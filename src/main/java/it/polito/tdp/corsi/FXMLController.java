/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Divisione;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import it.polito.tdp.corsi.model.Model;
import it.polito.tdp.corsi.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	//CREARE IL MODEL per permettere la comunicazione
	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	//input messo dall'utente e' sempre salvato come String
    	String input = txtPeriodo.getText();
    	
    	//variabile input che l'utente inserisce come intero
    	int inputNumerico = 0;
    	
    	
    	//CONTROLLI !!! 
    	try {
    		
    		// 1) se il  valore inserito E' un INTERO
    		inputNumerico = Integer.parseInt(input);
    	
    	}catch(NumberFormatException e) {
    		txtRisultato.setText("Inserted Value is not an integer value");
    	}
    	
    	//2) se IL VALORE INSERITO E' != DA 1 O 2 (vlori ammessibili per il periodo)
    	if (inputNumerico<1 || inputNumerico >2) {
    		txtRisultato.setText("Inserted 1 or 2");
    	}
    	
    	//arrivati a questo punto vuol dire che l'utente ha inerito l'input corretto
    	
    	//STRUTTURA DATI dove salvare i dati corretti
    	List<Corso> result = new ArrayList<>();
    	result = model.getCorsoByPeriodo(inputNumerico); //metodo che restituisce una lista di corsi --> quindi crearne una variabile lista
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Ho trovato " + result.size() + " corsi \n");
    	
    	//cosa restituire nel output
    	for (Corso c : result) {
    		txtRisultato.appendText(""+c+"\n");
    	}
    		
    }

    @FXML
    void numeroStudenti(ActionEvent event) {
    	
    //2 VARIABILI INPUT 
    	   //1) STRINGA
    	String input = txtPeriodo.getText();
    	   //2) INTERO
    	int inputNum = 0;
    	
    //CONTROLLI 
    	
    	//1) deve essere un intero
    	try {
    		inputNum = Integer.parseInt(input);
    		
    	}catch (NumberFormatException e) {
    		this.txtRisultato.setText("Inserire un valore intero");
    		return;
    	}
    	
    	//2) DEVE ESSERE PARI AD 1 O 2
    	if (inputNum < 1 || inputNum > 2) {
    		this.txtRisultato.setText("Inserire un valore pari ad 1 o 2");
    	}
    	
    	//in questo punto = utente ha inserito l'input CORRETTO
    	
    	//DOVE SALVARE I DATI DESIDERATI ?? 
    	//--> STRUTTURA DATI = MAPPA
    	Map<Corso, Integer> result = new HashMap<Corso, Integer>();
    	result = this.model.getCorsiIscritti(inputNum);
    	
    	//STAMPARE IL RISULTATO
    	txtRisultato.clear();
    	this.txtRisultato.setText("Ho trovato il numero di iscritti dei corsi del periodo " + inputNum + ": \n");
    	
    	for (Corso c : result.keySet()) {
    		txtRisultato.appendText(c + ": " + result.get(c) + "\n");
    	}
    	
    	
    }
    
  
    @FXML
    void stampaDivisione(ActionEvent event) {
    	
    	//CONSEGNA 4 : DATO UN CORSO, INDICARE LE DIVISIONI DEGLI STUDENTI IN BASE AL CDS
    	
    	//input 
    	String codins  =this.txtCorso.getText();
    	
    	//CONTROLLI
    	//deve esserci qualcosa nell'input
    	txtRisultato.clear();
    	if (codins.isEmpty()) {
    		txtRisultato.setText("Inserire un codice corso");
    	}
    	
    	//se tutto apposto
    	//invocare MODEL --> struttura dati dove salvare i dati
    	List<Divisione> result = new ArrayList<Divisione>();
    	result = this.model.getDivisioneStudentiCorso(codins);
    	
    	//stampare in output
    	txtRisultato.clear();
    	txtRisultato.setText("Dato il codice corso " + codins+ " le divisioni degli studenti sono le seguenti: \n");
    	
    	for (Divisione d : result) {
    		txtRisultato.appendText(d.getCDS() + ": " + d.getnStudenti()+"\n");
    	}

    }
    
    // CONSEGNA 3 : Elencare tutti gli studenti di un determinato corso
    
    //INPUT = codice del corso
    //OUTPUT = elenco degli studenti che appartengono a quel corso
    
    //per risolvere questa consegna e' necessario creare una classe Studente e StudenteDAO
    

    @FXML
    void stampaStudenti(ActionEvent event) {
    	
    	//input 
    	String codiceCorso = this.txtCorso.getText();
    	
    	//CONTROLLI
    	
    	//deve esserci effettivamente scritto qualcosa 
    	//poi se c'e' un intero non importa perche' nel controller viene salvato comunque come stringa quindi non ci sono problemi di consìversione. 
    	//non vale la stessa al contrario
    	
    	if (codiceCorso.isEmpty()) {
    		txtRisultato.setText("Inserire codice di un corso");
    		return;
    	}
    	
    	//se tutto apposto 
    	//creare una struttura dati dove salvare i dati desiderati
    	//INVOCANDO IL METODO PRESENTE NEL MODEL
    	List<Studente> elencoStudenti = new ArrayList<Studente>();
    	elencoStudenti = this.model.getIscrittiCorso(codiceCorso);
    	
    	txtRisultato.clear();
    	txtRisultato.setText("Gli studenti appartenenti al corso " + codiceCorso + " sono i seguenti : \n");
    	//STAMPARE IN OUTPUT IL RISULTATO
    	for (Studente s : elencoStudenti) {
    		txtRisultato.appendText(s.descrizioneStudente()+"\n");
    	}

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}