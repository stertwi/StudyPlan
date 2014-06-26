package org.gab.plane.client;


import com.google.gwt.user.client.rpc.RemoteService;

public interface ManagerService extends RemoteService{
	public boolean aggiungiCorso(String text, String text2, String text3, String text4, String text5);
	public boolean modCorso(String text, String text2);
	public boolean logout();
	public String visualizzaPiano(String text, String text2);
	public String getAtt(String att);
	
}
