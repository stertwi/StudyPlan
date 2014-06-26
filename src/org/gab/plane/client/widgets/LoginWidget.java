package org.gab.plane.client.widgets;


import javax.servlet.http.HttpSession;

import org.gab.plane.client.*;
import org.gab.plane.server.LoginServiceImpl;

import com.google.gwt.core.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.*;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.i18n.client.HasDirection.Direction;

public class LoginWidget extends Composite {
	
	private final LoginServiceAsync service;
    private final DockPanel panel = new DockPanel();
    private final Button button = new Button("Sign in");
    private final Label label = new Label("Benvenuto, Studente disorganizzato");
    private final FlexTable flexTable = new FlexTable();
    private final Label username = new Label("Matricola:");
    private final TextBox textBoxUsername = new TextBox();
    private final Label password = new Label("Password:");
    private final PasswordTextBox textBoxPassword = new PasswordTextBox();
    private final Hyperlink hprlnkRegistrat = new Hyperlink("Registrati", true, "register.jsp");
    private final Anchor hprlnkRegistrati = new Anchor("Registrati","http://localhost:8080/Piano/register.jsp");
    
    public LoginWidget() {
        // obtain a reference to the service
        service = (LoginServiceAsync) GWT.create(LoginService.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) service;
        endpoint.setServiceEntryPoint(GWT.getModuleBaseURL() + "data");
        
        service.getAtt("matricola", new AsyncCallback() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				Window.alert("errore verifica sessione: "+ caught.getMessage());
				
			}

			@Override
			public void onSuccess(Object result) {
				// TODO Auto-generated method stub
				if((String)result != null) {
//					Window.alert("sessione gia attiva per l'utente: " + (String )result);
					Window.Location.replace("http://localhost:8080/Piano/manager.jsp");
				}
				else {
					//Window.alert("matricola nulla: "+ (String )result);
				}
				
			}
        	
        });
        
        initWidget(panel);
        panel.add(label, DockPanel.CENTER);
        
        panel.add(flexTable, DockPanel.SOUTH);
        flexTable.setWidth("345px");
        username.setStyleName("gwt-Label-Login");
        
        flexTable.setWidget(0, 0, username);
        
        flexTable.setWidget(0, 1, textBoxUsername);
        password.setStyleName("gwt-Label-Login");
        
        flexTable.setWidget(1, 0, password);
        
        flexTable.setWidget(1, 1, textBoxPassword);
        
        
        flexTable.setWidget(3, 0, hprlnkRegistrati);
        flexTable.setWidget(3, 1, button);
        button.setWidth("57px");
        
        
        
        // click listener to get data from server
        button.addClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements ClickListener {
        public void onClick(Widget sender) {
            
        	if (textBoxUsername.getText().length() == 0
					|| textBoxPassword.getText().length() == 0) {
					Window.alert("Username o password is empty."); 
        	}
        	else {
        		// call servlet 
            	service.login(textBoxUsername.getText(), textBoxPassword.getText(), new AsyncCallback() {

    				@Override
    				public void onFailure(Throwable caught) {
    					// TODO Auto-generated method stub
    					Window.alert("Login fallito: " + caught.getMessage());
    					
    				}

    				@Override
    				public void onSuccess(Object result) {
    					// TODO Auto-generated method stub
    					if(result == null) {
    						Window.alert("Ops!! matricola o password errati.");
    					}
    					else {
    						Window.alert("Benvenuto " + (String)result);
        					Window.Location.replace("http://localhost:8080/Piano/manager.jsp");
    					}
    					
    				}
            		
            	});
        	}

        }
    }

}
