package org.gab.plane.server;

import org.gab.plane.client.RegisterService;
import org.gab.plane.shared.PlManTower;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

	@Override
	public boolean registra(String[] data) {
		// TODO Auto-generated method stub
		
		return PlManTower.creaStudente(data);
	}

	@Override
	public boolean registra2(String text, String text2, String text3,
			String text4, String text5, String text6, String text7) {
		// TODO Auto-generated method stub
		
		return PlManTower.creaStudente(text,text2,text3,text4,text5,text6,text7,this.getThreadLocalRequest().getSession());
	}

	@Override
	public String getAtt(String att) {
		// TODO Auto-generated method stub
		
		return (String) this.getThreadLocalRequest().getSession().getAttribute(att);
	}
	

}
