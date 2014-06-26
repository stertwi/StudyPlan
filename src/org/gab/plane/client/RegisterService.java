package org.gab.plane.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface RegisterService extends RemoteService{
	public boolean registra(String[] data);
	public boolean registra2(String text, String text2, String text3,
			String text4, String text5,String text6,String text7);
	 public String getAtt(String att);
}
