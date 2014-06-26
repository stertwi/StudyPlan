package org.gab.plane.test;

import java.util.Iterator;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.gab.plane.server.ManagerServiceImpl;
import org.gab.plane.server.RegisterServiceImpl;
import org.gab.plane.server.Sal;
import org.gab.plane.server.SalHome;
import org.gab.plane.server.Studente;
import org.gab.plane.server.StudenteHome;
import org.gab.plane.shared.Pianificazione;
import org.gab.plane.shared.PlManTower;
import org.w3c.dom.Document;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestGet {

	
	//private static Logger log;
		private static Logger log = Logger.getLogger(Test.class);
		
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger.getRootLogger().setLevel(Level.DEBUG);
		
//		SalHome sah2 = new SalHome();
//		Sal sal2 = sah2.findById(283);
//		log.info(sal2);
//		sah2.attachClean(sal2);
//		sah2.commit();
//		Pianificazione pian2 = new Pianificazione(sal2);
//		pian2.elaborazioneSal(sal2);
//		
//		
//		String xml2 = pian2.getPianificazioneXmlStr(sal2.getId());
//		log.debug(xml2);
//		String[] data = new String[5];
//		data[0] = "nome111111";
//		data[1] = "cognome";
//		data[2] = "matricola";
//		data[3] = "pass";
//		data[4] = "corsoo";
//		RegisterServiceImpl a = new RegisterServiceImpl();
//		
//		log.debug(a.registra(data) + a.getServletInfo() + "----" + a.toString());
		
//		Integer cont = 1;
//		String att = "sal"+(cont++).toString();
//		log.debug(att);
//		log.debug(cont);
		
//		log.debug(isThisDateValid("18/10/1983", "dd/MM/yyyy"));
//		String d = "18/10/1983";
//		log.debug(d.substring(0, 2));
//		log.debug(d.substring(3, 5));
//		log.debug(d.substring(6, 10));
		
//		Studente temp = new Studente();
//    	temp.setMatricola("stertwi");
//    	temp.setPwd("gabriele");
//    	StudenteHome sh  = new StudenteHome();
//    	
//    	List list = sh.findByExample(temp);
//		sh.commit();
//		Iterator it = list.iterator();
//		Studente stud = (Studente) it.next();
//		log.debug(stud);
		
		SalHome sh = new SalHome();
		Sal sal = sh.findById(291);
		sh.commit();
		try {
			sh.closeSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Pianificazione pian2 = new Pianificazione(sal);
		pian2.elaborazioneSal(sal);
		Document doc =  pian2.getPianificazioneXmlDoc(283);
		String[] ris = PlManTower.calcolaParametri(doc);
		for(int i=0;i<ris.length-1;i++) {
			log.info(ris[i]);
		}
	}
	
	public static boolean isThisDateValid(String dateToValidate, String dateFromat){
		 
		if(dateToValidate == null){
			return false;
		}
 
		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);
 
		try {
 
			//if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);
 
		} catch (ParseException e) {
 
			e.printStackTrace();
			return false;
		}
 
		return true;
	}

}
