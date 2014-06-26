package org.gab.plane.server;

import org.gab.plane.client.ManagerService;
import org.gab.plane.shared.PlManTower;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ManagerServiceImpl extends RemoteServiceServlet implements ManagerService {

	@Override
	public boolean aggiungiCorso(String text, String text2, String text3, String text4, String text5) {
		// TODO Auto-generated method stub
		
		return PlManTower.aggiungiCorso(text,text2,text3,text4,text5,this.getThreadLocalRequest().getSession());
	}

	@Override
	public boolean modCorso(String text, String text2) {
		// TODO Auto-generated method stub
		return PlManTower.modCorso(text,text2,this.getThreadLocalRequest().getSession());
	}

	@Override
	public boolean logout() {
		// TODO Auto-generated method stub
		try {
			this.getThreadLocalRequest().getSession().invalidate();
		}
		catch(IllegalStateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public String visualizzaPiano(String text, String text2) {
		// TODO Auto-generated method stub
		return PlManTower.visualizzaPiano(text,text2,this.getThreadLocalRequest().getSession());
	}

	@Override
	public String getAtt(String att) {
		// TODO Auto-generated method stub
		
		return (String) this.getThreadLocalRequest().getSession().getAttribute(att);
	}


}
