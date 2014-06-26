package org.gab.plane.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LoginServiceAsync {
    public void login(String usr, String pwd, AsyncCallback callback);
	public void getPiano(String id, AsyncCallback asyncCallback);
	public void getAtt(String string, AsyncCallback asyncCallback);

}
