package org.gab.plane.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ManagerServiceAsync {

	public void aggiungiCorso(String text, String text2, String text3, String text4,
			String text5, AsyncCallback asyncCallback);

	public void modCorso(String text, String text2, AsyncCallback asyncCallback);

	public void logout(AsyncCallback asyncCallback);

	public void visualizzaPiano(String text, String text2, AsyncCallback asyncCallback);
	public void getAtt(String string, AsyncCallback asyncCallback);

}
