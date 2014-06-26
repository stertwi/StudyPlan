package org.gab.plane.shared;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.gab.plane.server.Corso;
import org.gab.plane.server.CorsoHome;
import org.gab.plane.server.Piano;
import org.gab.plane.server.PianoHome;
import org.gab.plane.server.Sal;
import org.gab.plane.server.SalHome;
import org.gab.plane.server.Studente;
import org.gab.plane.server.StudenteHome;
import org.mortbay.log.Log;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.google.gwt.user.client.ui.HTML;

public class PlManTower {

//	public static List login(String usr, String pwd) {
//		// TODO Auto-generated method stub
////		String ris ="";
//		PlaneManager pl = new PlaneManager();
//		List list = pl.getByQuery("select studente from Studente as studente where matricola ='"+usr+"' and pwd='"+pwd+"'");
////		ris = list.iterator().next().toString();
//		try {
//			pl.closeSession();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return list;
//	}

	public static String getXmlByStudentId(String id) {
		// TODO Auto-generated method stub
		String ris ="";
		
		//estrapolo id SAL
		PlaneManager pl = new PlaneManager();
		List list = pl.getByQuery("select sal.id from Sal as sal where studente ='"+id+"'");
		String idSal = list.iterator().next().toString();
		try {
			pl.closeSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get xml
		Pianificazione pian = new Pianificazione();
		ris = pian.getPianificazioneXmlStr(Integer.parseInt(idSal));
		return ris;
	}
	
	public static String login(String usr,String pwd, HttpSession httpSession) {
    	
    	Studente temp = new Studente();
    	temp.setMatricola(usr);
    	temp.setPwd(pwd);
    	StudenteHome sh  = new StudenteHome();
    	
    	List list = sh.findByExample(temp);
    	if(list== null) {
    		Log.info("Nessuna corrispondenza per matricola e password");
    		return null;
    	}
    	Iterator it = list.iterator();
		Studente stud = (Studente) it.next();
		httpSession.setAttribute("idStudente", stud.getId().toString() );
		httpSession.setAttribute("nome", stud.getNome() );
		httpSession.setAttribute("cognome", stud.getCognome() );
		httpSession.setAttribute("matricola", stud.getMatricola() );
		httpSession.setAttribute("corsoDiLaurea", stud.getCorsoDiLaurea() );

		httpSession.setAttribute("user", usr);
		try {
			sh.commit();
			sh.closeSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PlaneManager pl = new PlaneManager();
		
		List listS = pl.getByQuery("select sal.id from Sal as sal where studente='"+stud.getId()+"'");
		if(!(listS.isEmpty())) {
			String idSal = listS.iterator().next().toString();
			httpSession.setAttribute("sal", idSal );
		}
		
		
		
		List listP = pl.getByQuery("select piano.id from Piano as piano where studente='"+stud.getId()+"'");
		if(!(listP.isEmpty())) {
			String idPiano = listP.iterator().next().toString();
			httpSession.setAttribute("piano", idPiano);
		}
		
		
		try {
			pl.closeSession();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return httpSession.getAttribute("nome").toString();
    	
    }

	public static boolean creaStudente(String[] data) {
		boolean ris =  false;
		try {
			StudenteHome sh = new StudenteHome();
			
			Studente studente = new Studente();
			studente.setNome(data[0]);
			studente.setCognome(data[1]);
			studente.setMatricola(data[2]);
			studente.setPwd(data[3]);
			studente.setCorsoDiLaurea(data[4]);
			
			sh.attachDirty(studente);
			sh.commit();
			sh.closeSession();
			ris = true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}		
		return ris;
	}

	public static boolean creaStudente(String text, String text2, String text3,
			String text4, String text5, String text6, String text7, HttpSession httpSession) {

		Boolean ris = false;
		StudenteHome sh = new StudenteHome();
		
		Studente studente = new Studente();
		studente.setNome(text);
		studente.setCognome(text2);
		studente.setMatricola(text3);
		studente.setPwd(text4);
		studente.setCorsoDiLaurea(text5);
		
		sh.attachDirty(studente);
		sh.commit();
		
		PianoHome ph = new PianoHome();
		
		Piano piano = new Piano();
		piano.setNome(text6);
		piano.setOreStudioGiorno(Double.parseDouble(text7));
		piano.setStudente(studente);
		ph.persist(piano);
		ph.commit();
		
		
		//aggiorno sessione
		httpSession.setAttribute("idStudente", studente.getId().toString() );
		httpSession.setAttribute("nome", text);
		httpSession.setAttribute("cognome", text2);
		httpSession.setAttribute("matricola", text3);
		httpSession.setAttribute("nome", text4);
		httpSession.setAttribute("corsoDiLaurea", text5);
		httpSession.setAttribute("piano", piano.getId().toString());
		httpSession.setAttribute("oreStudioGiorno", text7);
		
		ris = true;
		return ris;
		
	}

	public static boolean aggiungiCorso(String text, String text2,
			String text3, String text4, String text5, HttpSession session) {
		// TODO Auto-generated method stub
		try {
			CorsoHome ch = new CorsoHome();
			Corso corso = new Corso();
			corso.setNome(text);
			corso.setCfu(Double.parseDouble(text2));
			corso.setOreEffettuate(Double.parseDouble(text3));
			
			//data in timestamp
			String gg = text4.substring(0, 2);
			String mm = text4.substring(3, 5);
			String yyyy = text4.substring(6, 10);
			Timestamp dataEsame = new Timestamp(0).valueOf(yyyy+"-"+mm+"-"+gg+" 00:00:00");

			corso.setDataEsame(dataEsame);
			corso.setArgomenti(text5);
			
			ch.attachDirty(corso);
			ch.commit();
			
//			String idStud = (String) session.getAttribute("idStudente");
			String idPiano = (String) session.getAttribute("piano");
			
			PianoHome ph = new PianoHome();
			Piano piano = ph.findById(Integer.parseInt(idPiano));
			piano.getCorsi().add(corso);
			ph.persist(piano);
			ph.commit();
			
			
			
			
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static boolean modCorso(String text, String text2,
			HttpSession session) {
		// TODO Auto-generated method stub
		try {
			String idPiano = (String)session.getAttribute("piano");
			PlaneManager pl = new PlaneManager();
			List list = pl.getByQuery("select corso.id from Corso as corso where piano.id ='"+idPiano+"' and corso.nome = '"+text+"'");
			if(list == null) {
				return false;
			}
			pl.closeSession();
		
			CorsoHome ch = new CorsoHome();
			Corso corso = ch.findById((Integer)list.iterator().next());
			if(corso == null) {
				return false;
			}
			corso.setNome(text);
			corso.setOreEffettuate(Double.parseDouble(text2));
			ch.attachDirty(corso);
			ch.commit();
			ch.closeSession();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public static String visualizzaPiano(String text, String text2,
			HttpSession session) {
		// TODO Auto-generated method stub
		String html="";
		try {
			String[] ris= new String[20];
			String idSal = (String) session.getAttribute("sal");
			if( idSal == null) {
				Log.info("Sal non esiste, lo creo");
				//get Piano
				PianoHome ph = new PianoHome();
				Piano piano = ph.findById(Integer.parseInt((String)session.getAttribute("piano")));
				ph.commit();
				Log.info("piano : "+piano);
				ph.closeSession();
	//			try {
	//				
	//			} catch (Exception e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//			}
				
				StudenteHome sh = new StudenteHome();
				Log.info("idStudente: "+session.getAttribute("idStudente"));
				Studente studente = sh.findById(Integer.parseInt((String)session.getAttribute("idStudente")));
				sh.commit();
				Log.info("studente: "+studente);
				sh.closeSession();
	//			try {
	//				
	//			} catch (Exception e1) {
	//				// TODO Auto-generated catch block
	//				e1.printStackTrace();
	//			}
				
				//creo Sal e salvo in sessione, visto che l'attributo sal è null
				SalHome salh = new SalHome();
				Sal sal = new Sal();
				
				//data in timestamp
				String ggStart = text.substring(0, 2);
				String mmStart = text.substring(3, 5);
				String yyyyStart = text.substring(6, 10);
				Timestamp startStudio = new Timestamp(0).valueOf(yyyyStart+"-"+mmStart+"-"+ggStart+" 00:00:00");
				
				String ggFinish = text2.substring(0, 2);
				String mmFinish = text2.substring(3, 5);
				String yyyyFinish = text2.substring(6, 10);
				Timestamp finishStudio = new Timestamp(0).valueOf(yyyyFinish+"-"+mmFinish+"-"+ggFinish+" 00:00:00");
				
				sal.setStartStudio(startStudio);
				sal.setFinishStudio(finishStudio);
				sal.setPiano(piano);
				sal.setStudente(studente);
				
				Log.info("questo è il Sal prima del attachDirty: "+ sal);
				salh.attachDirty(sal);
				Log.info("questo è il Sal prime del commit: "+ sal);
				salh.commit();
				Log.info("questo è il Sal dopo il commit: "+ sal);
				salh.closeSession();
	//			try {
	//				
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
				
				SalHome salh2 = new SalHome();
				Sal sal2 = salh2.findById(sal.getId());
				sal2.aggiornaOreEffettuateTotali();
				salh2.attachDirty(sal2);
				salh2.commit();
				salh2.closeSession();
				
	//			try {
	//				
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
				
				Pianificazione pianif = new Pianificazione(sal2);
				pianif.elaborazioneSal(sal2);
				
				Document xml = pianif.getPianificazioneXmlDoc(sal2.getId());
				ris = calcolaParametri(xml);
				html = calcolaHtml(ris);
				
			}
			else {
				SalHome salh = new SalHome();
				Sal sal = salh.findById(Integer.parseInt(idSal));
				
				//data in timestamp
				String ggStart = text.substring(0, 2);
				String mmStart = text.substring(3, 5);
				String yyyyStart = text.substring(6, 10);
				Timestamp startStudio = new Timestamp(0).valueOf(yyyyStart+"-"+mmStart+"-"+ggStart+" 00:00:00");
				
				String ggFinish = text2.substring(0, 2);
				String mmFinish = text2.substring(3, 5);
				String yyyyFinish = text2.substring(6, 10);
				Timestamp finishStudio = new Timestamp(0).valueOf(yyyyFinish+"-"+mmFinish+"-"+ggFinish+" 00:00:00");
				
				sal.setStartStudio(startStudio);
				sal.setFinishStudio(finishStudio);
				salh.commit();
				salh.closeSession();
	//			try {
	//				
	//			} catch (Exception e) {
	//				// TODO Auto-generated catch block
	//				e.printStackTrace();
	//			}
				Pianificazione pianif = new Pianificazione(sal);
				pianif.elaborazioneSal(sal);
				
				Document xml = pianif.getPianificazioneXmlDoc(sal.getId());
				ris = calcolaParametri(xml);
				html = calcolaHtml(ris);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		return html;
	}

	

	public static String[] calcolaParametri(Document xml) {
		// TODO Auto-generated method stub
//		String[] ris = new String[20];
		
		Log.debug("in nodo Sal");
		Node nodeRoot = xml.getLastChild();
		Integer len = nodeRoot.getFirstChild().getChildNodes().getLength();
		Double totOreDaEffettuare = (double) 0;
		String[] ris = new String[len*3+4+1];
		ris[0] = nodeRoot.getAttributes().getNamedItem("oreADisposizione").getNodeValue();
		ris[2] = nodeRoot.getAttributes().getNamedItem("oreEffettuateTotali").getNodeValue();
		ris[3] = len.toString();
		ris[4] = nodeRoot.getFirstChild().getAttributes().getNamedItem("oreStudioGiorno").getNodeValue();
		
		Node nodeTemp = nodeRoot.getFirstChild().getFirstChild();
		int i = 5;
		while(nodeTemp!=null) {
			totOreDaEffettuare = totOreDaEffettuare + Double.parseDouble(nodeTemp.getLastChild().getAttributes().getNamedItem("totale").getNodeValue());
			
			Log.debug("in nodo Corso");
			ris[i] = nodeTemp.getAttributes().getNamedItem("name").getNodeValue();
			i=i+1;
			

			ris[i]= nodeTemp.getChildNodes().item(1).getAttributes().getNamedItem("totale").getNodeValue();
			totOreDaEffettuare = totOreDaEffettuare + Double.parseDouble(ris[i]);
			i=i+1;
			//oreEffettuate
			ris[i] = nodeTemp.getChildNodes().item(1).getChildNodes().item(0).getFirstChild().getNodeValue();
			i=i+1;
			nodeTemp = nodeTemp.getNextSibling();
		}
		
		ris[1] = totOreDaEffettuare.toString();
		return ris;
	}
	
	private static String calcolaHtml(String[] doc) {
		// TODO Auto-generated method stub
		Double oreADisposizione = Double.parseDouble(doc[0]);
		Double totOreDaEffettuare = Double.parseDouble(doc[1]);
		Double totOreEffettuate = Double.parseDouble(doc[2]);
		Double oreGiorno = Double.parseDouble(doc[4]);
		
		float perc1 = Math.round(totOreDaEffettuare/oreADisposizione*100);
		float perc2 = Math.round(totOreEffettuate/totOreDaEffettuare*100);
		
		String layoutData = "<html>" +
        		"				<head>" +
        		"					<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">" +
        		"					<title>Status</title>" +
        		"				</head>" +
        		"				<body>" +
        		"				<div id=\"oreADisposizione\" style=\"width: 100%; height: 40px; background :LIME; text-align: center; font-family: arial; font-size: 20px;\" >Ore A Disposizione "+oreADisposizione +"</div>" +
        		"				<div id=\"totOreDaEffettuare\" style=\"width:"+perc1+"%; height: 40px; background :GREEN; text-align: center; font-family: arial; font-size: 10px;\" > Da effettuare: "+totOreDaEffettuare+" - "+perc1+"%</div>" +
				"				<div id=\"totOreEffettuate\" style=\"width:"+perc2+"%; height: 40px; background :GREENYELLOW; text-align: center; font-family: arial; font-size: 10px;\" >Effettuate: "+totOreEffettuate+" - "+perc2+"%</div>";
   
        int cont =0;
        for(int i=5;cont<Integer.parseInt(doc[3]);i=i+3) {
        	String nomeCorso = doc[i];
        	Double oreStudio = Double.parseDouble(doc[i+1]);
        	Double oreEffettuate = Double.parseDouble(doc[i+2]);
        	
        	float perc3 = Math.round(oreStudio/totOreDaEffettuare*100);
        	float perc4 = Math.round(oreEffettuate/oreStudio*100);
        	
        	String background = "GRAY";
        	if(i % 2 == 0) {
        		background = "LIGHTGRAY";
        	}
        	
        	layoutData = layoutData /*+ "<div id=\""+nomeCorso+"\"style=\"width:"+perc3+"%; height: 40px; background :GRAY; text-align: center; font-family: arial; font-size: 10px;\" >"+nomeCorso+" "+perc3+"%</div>"*/ +
            		"				   <div id=\"oreEffettuate"+nomeCorso+"\" style=\"width:"+perc4+"%; height: 40px; background :"+background+"; text-align: center; font-family: arial; font-size: 10px;\" >"+nomeCorso+ " " +perc4+"%</div>";
        	cont++;
		}
        
        float perc5 = Math.round(oreGiorno/oreADisposizione*100);
        layoutData = layoutData +/*"	<div id=\"blank\" style=\"width:1%; height: 40px; text-align: center; font-family: arial; font-size: 10px;\" ></div>" +*/
        						"	<div id=\"oreGiorno\" style=\" height: 40px; background :RED; text-align: center; font-family: arial; font-size: 10px;\" > ore/giorno: "+doc[4]+"</div></body></html>";
        
        
		return layoutData;
	}
	

}
