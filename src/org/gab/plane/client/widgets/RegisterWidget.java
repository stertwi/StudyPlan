package org.gab.plane.client.widgets;

import org.gab.plane.client.LoginService;
import org.gab.plane.client.LoginServiceAsync;
import org.gab.plane.client.RegisterService;
import org.gab.plane.client.RegisterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.client.ui.InlineLabel;


public class RegisterWidget extends Composite {
	
	private final RegisterServiceAsync service;
	private TextBox nomeTextBox = new TextBox();
	private TextBox cognomeTextBox = new TextBox();;
	private TextBox matricolaTextBox = new TextBox();
	private PasswordTextBox passwordTextBox = new PasswordTextBox();
	private TextBox corsoDiLaureaTextBox = new TextBox();
	private TextBox nomePianoTextbox = new TextBox();
	private TextBox oreStudioGiornoTextBox = new TextBox();
	
	public RegisterWidget() {
		
		service = (RegisterServiceAsync) GWT.create(RegisterService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "data");
		
		DockPanel dockPanel = new DockPanel();
		initWidget(dockPanel);
		
		Label lblInserisciITuoi = new Label("Inserisci i tuoi dati");
		dockPanel.add(lblInserisciITuoi, DockPanel.CENTER);
		lblInserisciITuoi.setHeight("39px");
		
		FlexTable flexTable = new FlexTable();
		dockPanel.add(flexTable, DockPanel.SOUTH);
		flexTable.setWidth("345px");
		
		Label lblNome = new Label("Nome");
		lblNome.setStyleName("gwt-Label-Login");
		flexTable.setWidget(0, 0, lblNome);
		
		
		flexTable.setWidget(0, 1, nomeTextBox);
		
		
		Label lblCognome = new Label("Cognome");
		lblCognome.setStyleName("gwt-Label-Login");
		flexTable.setWidget(1, 0, lblCognome);
		
		
		flexTable.setWidget(1, 1, cognomeTextBox);
		
		
		Label lblMatricola = new Label("Matricola");
		flexTable.setWidget(2, 0, lblMatricola);
		
		
		flexTable.setWidget(2, 1, matricolaTextBox);
		
		
		Label lblPassword = new Label("Password");
		flexTable.setWidget(3, 0, lblPassword);
		
		
		flexTable.setWidget(3, 1, passwordTextBox);
		
		Label lblCorsoDiLaurea = new Label("Corso Di Laurea");
		flexTable.setWidget(4, 0, lblCorsoDiLaurea);
		
		
		flexTable.setWidget(4, 1, corsoDiLaureaTextBox);
		
		Label lblNomePiano = new Label("Nome Piano");
		flexTable.setWidget(6, 0, lblNomePiano);
		
		
		flexTable.setWidget(6, 1, nomePianoTextbox);
		
		Label lblOreStudio = new Label("Ore/Giorno di studio previsto");
		flexTable.setWidget(7, 0, lblOreStudio);
		
		
		flexTable.setWidget(7, 1, oreStudioGiornoTextBox);
		
		Button button = new Button("Sign in");
		flexTable.setWidget(9, 1, button);
		button.setWidth("57px");
		flexTable.getCellFormatter().setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		flexTable.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		
		InlineLabel nlnlblNewInlinelabel = new InlineLabel("Ai sensi del DM 270/04, che rappresenta la vigente normativa sulla riforma universitaria, a ogni CFU corrispondono di norma 25 ore di impegno complessivo per studente. Ci\u00F2 significa che le 25 ore includono sia le ore dedicate in aula alle classiche lezioni frontali erogate dal docente, sia lo studio individuale per il necessario apprendimento della disciplina. In genere, le 25 ore relative a ciascun CFU sono riservate per un terzo (e quindi circa 7-8 ore/CFU) alle lezioni in aula e per due terzi (circa 17-18 ore/CFU) allo studio individuale. Questa suddivisione pu\u00F2 cambiare nel caso di attivit\u00E0 formative prevalentemente pratiche, come esercitazioni in aula o in laboratorio, in cui i due terzi delle ore relative a ciascun CFU possono essere dedicati alle attivit\u00E0 esercitative e il resto allo studio individuale.");
		dockPanel.add(nlnlblNewInlinelabel, DockPanel.SOUTH);
		nlnlblNewInlinelabel.setHeight("198px");
		
		
		button.addClickListener(new ButtonClickListener());
	}
	
	private class ButtonClickListener implements ClickListener {
		public void onClick(Widget sender) {
			if (nomeTextBox.getText().length() == 0
					|| cognomeTextBox.getText().length() == 0
					|| matricolaTextBox.getText().length()== 0
					|| passwordTextBox.getText().length() == 0
					|| corsoDiLaureaTextBox.getText().length() == 0
					|| nomePianoTextbox.getText().length() == 0
					|| oreStudioGiornoTextBox.getText().length() == 0) {
					Window.alert("Tutti i campi sono obbligatori."); 
        	}
			else if(!(isInteger(oreStudioGiornoTextBox.getText()))) {
				Window.alert("Inserire un intero nel campo Ore/Giorno"); 
			}
			else {
				service.registra2(nomeTextBox.getText().toUpperCase(),cognomeTextBox.getText().toUpperCase(),
						matricolaTextBox.getText(),passwordTextBox.getText(),corsoDiLaureaTextBox.getText().toUpperCase(),
						nomePianoTextbox.getText().toUpperCase(), oreStudioGiornoTextBox.getText().toUpperCase(),new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Ops!!Errore imprevisto!!! riprova in seguito: "+ caught.getMessage());
						
					}

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						if((Boolean)result) {
							Window.alert("Registrazione effettuata, procedo all'autologin");
							Window.Location.replace("http://localhost:8080/Piano");

						}
						else {
							Window.alert("Ops!! Errore in creazione riprova più tardi");
						}
						
					}
	        		
	        	});
			}
        	
        }
	}
	
	private boolean isInteger(String string) {
	    try {
	        Integer.valueOf(string);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
        
}
