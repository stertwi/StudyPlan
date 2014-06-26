package org.gab.plane.client.widgets;



import java.util.Date;

import org.gab.plane.client.LoginService;
import org.gab.plane.client.LoginServiceAsync;
import org.gab.plane.client.ManagerService;
import org.gab.plane.client.ManagerServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockPanel;
import com.google.gwt.user.client.ui.DoubleBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;


public class ManagerWidget extends Composite {
	
	private final ManagerServiceAsync service;
	
	private TextBox nomeTextBox;
	private TextBox cfuTextBox;
	private DoubleBox oreEffettuateTextBox;
	private TextBox dataEsameTextBox;
	private TextBox argomentiTextBox;
	private FlexTable flexTable;
	private TextBox nomeModTextBox;
	private TextBox oreEffettuateModTextBox;
	
	private Button btnAggiungiCorso;
	private Button btnVisualizzaPiano;	
	private Button btnLogout;
	private Button btnModificaCorso;
	private TextBox startStudiotextBox;
	private TextBox finishStudioTextBox;
	private Label lblStartStudio;
	private Label lblFinishStudio;
	private DockPanel dockpanel;
	private HTML status = new HTML("status");
	private FlexTable flexTableVisual;
    
	
	@SuppressWarnings("deprecation")
	public ManagerWidget() {
		
		service = (ManagerServiceAsync) GWT.create(ManagerService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
	    endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "data");
	    
	    
		//verifica sessione
		service.getAtt("matricola", new AsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("errore verifica sessione");
				
			}

			@Override
			public void onSuccess(Object result) {
				// TODO Auto-generated method stub
				if((String)result == null) {
//					Window.alert("sessione gia attiva per l'utente: " + (String )result);
					Window.Location.replace("http://localhost:8080/Piano/index.jsp");
				}
				
			}
        	
        });
	    
	    
		flexTable = new FlexTable();
		initWidget(flexTable);
		flexTable.setSize("800", "600");
		status.setSize("662px", "316px");
		
		btnLogout = new Button("Logout");
		flexTable.setWidget(0, 3, btnLogout);
		
		btnLogout.addClickListener(new ClickListener() {

			@Override
			@Deprecated
			public void onClick(Widget sender) {
				// TODO Auto-generated method stub
				service.logout(new AsyncCallback() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						Window.alert("Ops!! logout fallito: "+ caught.getMessage());
					}

					@Override
					public void onSuccess(Object result) {
						// TODO Auto-generated method stub
						Window.alert("Arrivederci!!!");
						Window.Location.replace("http://localhost:8080/Piano");
						
					}
					
				});
				
			}
			
		});
		
		lblStartStudio = new Label("Start Studio");
		flexTable.setWidget(2, 1, lblStartStudio);
		
		startStudiotextBox = new TextBox();
		startStudiotextBox.setText("GG/MM/AAAA");
		flexTable.setWidget(2, 2, startStudiotextBox);
		
		lblFinishStudio = new Label("Finish Studio");
		flexTable.setWidget(3, 1, lblFinishStudio);
		
		finishStudioTextBox = new TextBox();
		finishStudioTextBox.setText("GG/MM/AAAA");
		flexTable.setWidget(3, 2, finishStudioTextBox);
		
		btnVisualizzaPiano = new Button("New button");
		btnVisualizzaPiano.setText("Visualizza Piano di Studi");
		flexTable.setWidget(4, 2, btnVisualizzaPiano);
		
		btnVisualizzaPiano.addClickListener(new ClickListener() {

			@Override
			@Deprecated
			public void onClick(Widget sender) {
				// TODO Auto-generated method stub
				if(startStudiotextBox.getText().length() == 0 || finishStudioTextBox.getText().length() == 0) {
					Window.alert("start e finish sono campi obbligatori");
				}
				else if(!(isThisDateValid(startStudiotextBox.getText(),"dd/MM/yyyy") || isThisDateValid(finishStudioTextBox.getText(),"dd/MM/yyyy") )) {
					Window.alert("formato data non valido, inserire dd/MM/yyyy");
				}
				
				else {
					service.visualizzaPiano(startStudiotextBox.getText(),finishStudioTextBox.getText(),new AsyncCallback() {

						

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("Ops!!! errore imprevisto, riprova in seguito:  "+ caught.getMessage());
							
						}

						@Override
						public void onSuccess(Object result) {
							// TODO Auto-generated method stub
							if(result == null) {
								Window.alert("Ops!! Errore in visualizza corso");
							}
							else {
								Window.alert("OK da qui creo il piano visuale");
								
								flexTableVisual = new FlexTable();
								flexTable.setWidget(4, 3, flexTableVisual);
								flexTableVisual.setSize("273px", "182px");
								
//						        flexTableVisual.setWidth("640px");
						        
						        status.setHTML((String)result);
						        flexTableVisual.setWidget(1, 0, status);
							}
							
					        
						}
						
					});
				}
				
			}
			
		});
		
		
		
		InlineLabel nlnlblAggiungiCorso = new InlineLabel("Aggiungi Corso");
		flexTable.setWidget(5, 0, nlnlblAggiungiCorso);
		nlnlblAggiungiCorso.setSize("110px", "30px");
		
		Label lblNome = new Label("Nome");
		flexTable.setWidget(6, 1, lblNome);
		
		nomeTextBox = new TextBox();
		flexTable.setWidget(6, 2, nomeTextBox);
		
		Label lblCfu = new Label("CFU");
		flexTable.setWidget(7, 1, lblCfu);
		
		cfuTextBox = new TextBox();
		flexTable.setWidget(7, 2, cfuTextBox);
		
		Label lblDataEsame = new Label("Data Esame");
		flexTable.setWidget(8, 1, lblDataEsame);
		
		dataEsameTextBox = new TextBox();
		dataEsameTextBox.setText("GG/MM/AAAA");
		flexTable.setWidget(8, 2, dataEsameTextBox);
		
		Label lblOreEffettuate = new Label("Ore Effettuate");
		flexTable.setWidget(9, 1, lblOreEffettuate);
		
		oreEffettuateTextBox = new DoubleBox();
		flexTable.setWidget(9, 2, oreEffettuateTextBox);
		
		Label lblArgomenti = new Label("Argomenti");
		flexTable.setWidget(10, 1, lblArgomenti);
		
		argomentiTextBox = new TextBox();
		flexTable.setWidget(10, 2, argomentiTextBox);
		
		btnAggiungiCorso = new Button("Aggiungi Corso");
		flexTable.setWidget(11, 2, btnAggiungiCorso);
		
		InlineLabel nlnlblModificaCorso = new InlineLabel("Modifica Corso");
		flexTable.setWidget(13, 0, nlnlblModificaCorso);
		
		Label lblNomeMod = new Label("Nome");
		flexTable.setWidget(14, 1, lblNomeMod);
		
		nomeModTextBox = new TextBox();
		flexTable.setWidget(14, 2, nomeModTextBox);
		
		Label lblOreEffettuate_1 = new Label("Ore Effettuate");
		flexTable.setWidget(15, 1, lblOreEffettuate_1);
		
		oreEffettuateModTextBox = new TextBox();
		flexTable.setWidget(15, 2, oreEffettuateModTextBox);
		flexTable.getCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_RIGHT);
		
		btnModificaCorso = new Button("Modifica Corso");
		flexTable.setWidget(16, 2, btnModificaCorso);
		

		
		btnAggiungiCorso.addClickListener(new ClickListener() {

			@Override
			@Deprecated
			public void onClick(Widget sender) {
				// TODO Auto-generated method stub
				if(nomeTextBox.getText().length() == 0
						|| cfuTextBox.getText().length() == 0
						|| oreEffettuateTextBox.getText().length() == 0) {
					Window.alert("nome, cfu e ore sono campi obbligatori");
				}
				else if(!(isInteger(cfuTextBox.getText()))) {
					Window.alert("Ops!!! inserisci un intero nel campo CFU");
				}
				else if( !(isInteger(oreEffettuateTextBox.getText()))) {
					Window.alert("Ops!!! inserisci un intero nel campo Ore Effettuate");
				}
				else if(!(isThisDateValid(dataEsameTextBox.getText(),"dd/MM/yyyy") )) {
					Window.alert("formato data non valido, inserire dd/MM/yyyy");
				}
				else {
					service.aggiungiCorso(nomeTextBox.getText().toUpperCase(),cfuTextBox.getText(),oreEffettuateTextBox.getText(),
							dataEsameTextBox.getText(),argomentiTextBox.getText().toUpperCase(), new AsyncCallback() {

								@Override
								public void onFailure(Throwable caught) {
									// TODO Auto-generated method stub
									Window.alert("Ops!! Aggiungi corso fallito: "+ caught.getMessage());
								}

								@Override
								public void onSuccess(Object result) {
									// TODO Auto-generated method stub
									if((Boolean)result) {
										Window.alert("OK, corso aggiunto correttamente");
									}
									else {
										Window.alert("Ops!! Errore in aggiunta Corso");
									}
								}
						
					});
				}
			}
			
			
			
			
		});
		
		btnModificaCorso.addClickListener(new ClickListener() {

			@Override
			@Deprecated
			public void onClick(Widget sender) {
				// TODO Auto-generated method stub
				if(nomeModTextBox.getText().length() == 0
						|| oreEffettuateModTextBox.getText().length() == 0) {
					Window.alert("nome e ore effettuate sono campi obbligatori");
				}
				else if(!(isInteger(oreEffettuateModTextBox.getText()))) {
					Window.alert("Ops!!! inserisci un intero nel campo Ore Effettuate");
				}
				else {
					service.modCorso(nomeModTextBox.getText().toUpperCase(),oreEffettuateModTextBox.getText(), new AsyncCallback() {

						@Override
						public void onFailure(Throwable caught) {
							// TODO Auto-generated method stub
							Window.alert("Ops!! corso non modificato, riprova più tardi");
						}

						@Override
						public void onSuccess(Object result) {
							// TODO Auto-generated method stub
							if((Boolean)result)  {
								Window.alert("corso modificato con successo");
//								Window.Location.replace("http://localhost:8080/Piano/manager.jsp");
							}
							else {
								Window.alert("Ops! errore in modifica, corso non trovato");
							}
							
						}
						
					});
				}
			}
			
		});
	}
	
	
	
	
	
	
	
	
	










	private boolean isThisDateValid(String dateToValidate, String dateFromat) {
		 
		if(dateToValidate == null || dateToValidate.length()!=10){
			return false;
		}
		try {
//		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
//		sdf.setLenient(false);
		
		DateTimeFormat myDateTimeFormat = DateTimeFormat.getFormat(dateFromat);
 
		
 
			//if not valid, it will throw ParseException
			Date date = myDateTimeFormat.parse(dateToValidate);
//			Date date = myDateTimeFormat.parseStrict(dateToValidate);
//			System.out.println(date);
 
		} catch (IllegalArgumentException  e) {
 
			e.printStackTrace();
			return false;
		}
 
		return true;
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
