package org.gab.plane.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {
	public void registra(String[] data, AsyncCallback callback);
	public void registra2(String text, String text2, String text3,
			String text4, String text5, String text6, String text7, AsyncCallback asyncCallback);
	public void getAtt(String string, AsyncCallback asyncCallback);
}
