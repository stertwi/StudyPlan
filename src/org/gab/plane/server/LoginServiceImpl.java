package org.gab.plane.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;



import org.gab.plane.client.LoginService;
import org.gab.plane.shared.PlManTower;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
			
	
    
    public String login(String usr,String pwd) {
    	
    	return PlManTower.login(usr,pwd,this.getThreadLocalRequest().getSession());
    	
    }

	@Override
	public String getPiano(String id) {
		// TODO Auto-generated method stub
		String xml = PlManTower.getXmlByStudentId((String) id);
		return xml;
	}

	@Override
	public String getAtt(String att) {
		// TODO Auto-generated method stub
		
		return (String) this.getThreadLocalRequest().getSession().getAttribute(att);
	}
    
}
