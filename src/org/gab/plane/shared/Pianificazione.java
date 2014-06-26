package org.gab.plane.shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import oracle.xdb.XMLType;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import oracle.jdbc.OraclePreparedStatement;
import oracle.jdbc.OracleResultSet;
import oracle.sql.DATE;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gab.plane.server.Corso;
import org.gab.plane.server.Sal;
import org.gab.plane.server.SalHome; 

import org.w3c.dom.*;

import com.google.appengine.labs.repackaged.com.google.common.io.Files;


public class Pianificazione {
	
	private static final Log log = LogFactory.getLog(Pianificazione.class);
	
	private Sal sal;
		
	public Pianificazione () {
		
	}
	
	public Pianificazione(Sal sal) {
		this.sal = sal;
	}
	
	public void elaborazioneSal(Sal sal) {
		log.debug("init ElaborazioneSal SAL");
		log.info("\n" +
				"\t* Ai sensi del DM 270/04, che rappresenta la vigente normativa sulla riforma universitaria,*******\n"+
				"\t*  a ogni CFU corrispondono di norma 25 ore di impegno complessivo per studente.******************\n"+
				"\t*  Ciò significa che le 25 ore includono sia le ore dedicate in aula alle classiche **************\n"+
				"\t*  lezioni frontali erogate dal docente, sia lo studio individuale per il necessario *************\n"+
				"\t*  apprendimento della disciplina. In genere, le 25 ore relative a ciascun CFU sono **************\n"+
				"\t*  riservate per un terzo (e quindi circa 7-8 ore/CFU) alle lezioni in aula e per ****************\n"+
				"\t*  due terzi (circa 17-18 ore/CFU) allo studio individuale. Questa suddivisione può **************\n"+
				"\t*  cambiare nel caso di attività formative prevalentemente pratiche, come esercitazioni **********\n"+
				"\t*  in aula o in laboratorio, in cui i due terzi delle ore relative a ciascun CFU possono *********\n"+
				"\t*  essere dedicati alle attività esercitative e il resto allo studio individuale. ****************\n");
		
		SalHome salHome = new SalHome();
		Sal salTemp = salHome.findById(sal.getId());
		
		Double oreStudioGiorno = salTemp.getPiano().getOreStudioGiorno();
		
		
//		Double oreStudioGiorno = sal.getPiano().getOreStudioGiorno();
		if(sal.getStudente() == null) {
			log.debug("studente non inizializzato in SAL");
		}
		else if(sal.getPiano() == null) {
			log.debug("piano non inizializzato in SAL");
		}
		else if(sal.getStartStudio() == null) {
			log.debug("startStudio non inizializzato in SAL");
		}
		else if(sal.getFinishStudio() == null) {
			log.debug("finishStudio non inizializzato in SAL");
		}
		else {
			log.debug("OK tutti i campi sono inizializzati");
			log.debug("Calcolo differenza tra le date di inizio e fine studio");
			long millisDiff = salTemp.getFinishStudio().getTime() - salTemp.getStartStudio().getTime();
			log.debug("la differenza in millisecondi è pari a: " + millisDiff);
//			int seconds = (int) (millisDiff / 1000 % 60);
//			int minutes = (int) (millisDiff / 60000 % 60);
//			int hours = (int) (millisDiff / 3600000 % 24);
//			int days = (int) (millisDiff / 86400000);
			
			Double oreADisposizione = (double) (millisDiff / 3600000);
			log.debug("ore totali a disposizione, riferendosi a startStudio e FinishStudio: " + oreADisposizione);
			Double giorniStudio = oreADisposizione/oreStudioGiorno;
			log.debug("ore studio al giorno : " + giorniStudio);
			
			log.debug("get della lista dei corsi per il piano associato a this SAL");
			Set<Corso> corsi = (Set<Corso>) salTemp.getPiano().getCorsi();
			
//			salHome.commit();
//			try {
//				salHome.closeSession();
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			
			log.debug("verifico se si tratta di prima elaborazione, o di rielaborazione");
			String xml;
			try {
				if(true/*sal.getPianificazione() == 0*/) {
					log.debug("questa è la prima elaborazione");
					log.debug("invoco creoXml per con i parametri ottenuti");
					xml = creoXml(corsi, giorniStudio, oreADisposizione, oreStudioGiorno, sal.getOreEffettuateTotali(), sal.getId());
					
					log.debug("incremento il contatore delle pianificazioni");
//					SalHome sah = new SalHome();
					Sal temp = salHome.findById(sal.getId());
					temp.setPianificazione(sal.getPianificazione() + 1);
					salHome.attachClean(temp);
					salHome.commit();
					salHome.closeSession();
				}
				else {
					log.debug("Xml già presente per il SAL selezionato, quindi procedo alla rielaborazione");
					log.debug("invoco rielaboraXml per con i parametri ottenuti");
					xml = rielaboroXml(corsi, giorniStudio, oreADisposizione, oreStudioGiorno, sal.getOreEffettuateTotali(), sal.getId());
					
					log.debug("incremento il contatore delle pianificazioni");
					SalHome sah = new SalHome();
					Sal temp = sah.findById(sal.getId());
					temp.setPianificazione(sal.getPianificazione() + 1);
					sah.attachClean(temp);
					sah.commit();
					sah.closeSession();
				}
				log.debug("insert file xml nel db");
				doInsert(sal.getId(), xml);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}
	
	
	
	private String creoXml(Set<Corso> corsi, Double giorniStudio, Double oreADisposizione, Double oreStudioGiorno, Double oreEffettuateTotali, Integer salId) {
			log.debug("in creoXml");
			String ris = "";
		 try {
			 
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

				// root elements
				log.debug("creo rootElement");
				Document doc = docBuilder.newDocument();
				Element rootElement = doc.createElement("Sal");
				doc.appendChild(rootElement);
		 
				// Corsi elements
				log.debug("creo elemento corsi");
				Element corsiTag = doc.createElement("corsi");
				rootElement.setAttribute("id", salId.toString());
				rootElement.appendChild(corsiTag);
				rootElement.setAttribute("oreADisposizione", oreADisposizione.toString());
				rootElement.setAttribute("oreEffettuateTotali", oreEffettuateTotali.toString());
				
				// set attribute to staff element
				log.debug("setto attributi corsi");
				corsiTag.setAttribute("oreStudioGiorno", oreStudioGiorno.toString());
				
				log.debug("inizializzo Iterator per scandire il Set<Corsi> preso in input");
				for (Iterator<Corso> iter = corsi.iterator(); iter.hasNext();) {
					Corso element = (Corso) iter.next();
					log.debug(element);
					
					Element corso = doc.createElement("corso");
					corso.setAttribute("name", element.getNome());
					corso.setAttribute("id", element.getId().toString());
					corso.setAttribute("completed", "false");
					corsiTag.appendChild(corso);
					
					Element cfu = doc.createElement("cfu");
					cfu.appendChild(doc.createTextNode(Double.toString(element.getCfu())));
					corso.appendChild(cfu);
					
					Element oreStudio = doc.createElement("oreStudio");
					oreStudio.setAttribute("totale", Double.toString(element.getCfu()*18));
					corso.appendChild(oreStudio);
						
						Element oreEffettuateTag = doc.createElement("oreEffettuate");
						oreEffettuateTag.appendChild(doc.createTextNode((Double.toString(element.getOreEffettuate()))));
						oreStudio.appendChild(oreEffettuateTag);
						
//						Element oreADisposizioneTag = doc.createElement("oreADisposizione");
//						oreADisposizioneTag.appendChild(doc.createTextNode(oreADisposizione.toString()));
//						oreStudio.appendChild(oreADisposizioneTag);
				}
					

				// write the content into xml file
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
//				File folder = new File("src/xmlTemp");
//			    folder.mkdir();
//			    File fileXml = new File(folder,"xmlToDb.xml");
				log.debug("Creo file in directory C:/");
				File fileXml = new File("xmlToDb.xml");
				StreamResult result = new StreamResult(fileXml);
		 
				// Output to console for testing
				// StreamResult result = new StreamResult(System.out);
		 
				transformer.transform(source, result);
				
				
				BufferedReader reader =	new BufferedReader(	new FileReader(fileXml) );
				String temp = "";
				while( (temp = reader.readLine()) != null ) {
					ris = ris + temp;
					log.debug("file xml: " + ris);
		
				}
					
				
				reader.close();
		    	File folder = new File("src/xmlTemp");
			    folder.mkdir();
			    File f = new File(folder, "xmltoDb_" + System.currentTimeMillis() + ".xml");
//			        RandomAccessFile ar = new RandomAccessFile(f, "rw");
		        
//			        ar.writeChars("Questo file è solo un esempio!");
//			        ar.close();
		        Files.copy(fileXml,f);
		        log.debug("delete xml temp ---> " + fileXml.delete());
				log.debug("File saved!");

		 
			  } catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			  } catch (TransformerException tfe) {
				tfe.printStackTrace();
			  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
		return ris;
	}
	
	
	private String rielaboroXml(Set<Corso> corsi, Double giorniStudio, Double oreADisposizione, Double oreStudioGiorno, Double oreEffettuateTotali, Integer salId) {
		log.debug("in rielaboroXml");
		String ris ="";
		if(sal.getPianificazione() == 0) {
			log.debug("il contatore delle pianificazione è pari a 0, quindi siamo alla prima elaborazione invoco creo Xml");
			return creoXml(corsi,giorniStudio,oreADisposizione,oreStudioGiorno,oreEffettuateTotali,salId);
		}
		Document doc = getPianificazioneXmlDoc(salId);
		
		//**************** momentaneo risulato DA CAMBIARE ****************
//		ris = getPianificazioneXmlStr(salId);
		//****************momentaneo risulato DA CAMBIARE ****************
		
//		Element rootE = doc.getDocumentElement();
		Node nodeRoot = doc.getLastChild();
		Node node = nodeRoot;
		log.debug(node.getLocalName());
		
		if(node.getLocalName().equals("Sal")) {
			log.debug("tag Sal, estraggo id");
			NamedNodeMap map = node.getAttributes();
			if(map.getNamedItem("id").getNodeValue().equals(salId.toString())) {
				log.debug("OK salId matcha con l'attributo id del tag Sal");
			}
			else {
				log.debug("KO salId non matcha con l'attributo id del tag Sal");
				return null;
			}
			log.debug("aggiorno l'attributo oreADisposizione");
			map.getNamedItem("oreADisposizione").setNodeValue(oreADisposizione.toString());
			log.debug("aggiorno l'attributo oreEffettuateTotali");
			map.getNamedItem("oreEffettuateTotali").setNodeValue(oreEffettuateTotali.toString());
		}
		log.debug("Mi posiziono sul tag corsi");
		node = node.getFirstChild();
		log.debug("aggiorno l'attributo oreStudioGiorno");
		NamedNodeMap map = node.getAttributes();
		map.getNamedItem("oreStudioGiorno").setNodeValue(oreStudioGiorno.toString());
		
		int cont = 1;
		node = node.getFirstChild();
		while(node != null) {
			
			log.debug("corso n° " + cont++);
			NamedNodeMap map2 = node.getAttributes();
			String id = map2.getNamedItem("id").getNodeValue();
			Double oreEffettuateNew = null;
			
			log.debug("cerco il corso nel set in input ed estraggo le ore effettuate per tale corso");
			for(Iterator it = corsi.iterator();it.hasNext();) {
				Corso tmpCors = (Corso) it.next();
				if(tmpCors.getId().toString().equals(id)) {
					log.debug("Corso trovato salvo il valore di OreEffettuare");
					oreEffettuateNew = tmpCors.getOreEffettuate();
					break;
				}
			}
			
			if(oreEffettuateNew == null) {
				log.error("Id : " + id + " non trovato nel Set corsi");
			}
			
			String name = map2.getNamedItem("name").getNodeValue();
			String completed = map2.getNamedItem("completed").getNodeValue();
			log.debug("Id: " + id);
			log.debug("Name: " + name);
			log.debug("Completed: " + completed);
			
			if(completed.equals("true")) {
				node = node.getNextSibling();
			}
			else {
				String cfu = node.getChildNodes().item(0).getFirstChild().getNodeValue();
				log.debug("Per il corso ["+name +" - " +id + "] - CFU: " + cfu);
				
				NamedNodeMap mapOreStudio = node.getChildNodes().item(1).getAttributes();
				String oreStudioTotale = mapOreStudio.getNamedItem("totale").getNodeValue();
				log.debug("Per il corso ["+name +" - " +id + "] - OreStudio TOTALE : "+ oreStudioTotale);
				
				String oreEffettuate = node.getChildNodes().item(1).getChildNodes().item(0).getFirstChild().getNodeValue();
				log.debug("Per il corso ["+name +" - " +id + "] - ORE EFFETTUATE : " + oreEffettuate);
				log.debug("Provvedo all'update del valore OreEffettuate per il corso in esame");
				node.getChildNodes().item(1).getChildNodes().item(0).getFirstChild().setNodeValue(oreEffettuateNew.toString());
				oreEffettuate = node.getChildNodes().item(1).getChildNodes().item(0).getFirstChild().getNodeValue();
				log.debug("AGGIORNAMENTO Per il corso ["+name +" - " +id + "] - ORE EFFETTUATE : " + oreEffettuate);
				
				log.debug("Aggiorno le oreEffettuateTotali nel tag Sal");
				oreEffettuateTotali = oreEffettuateTotali + Double.parseDouble(oreEffettuate);
				
				
				log.debug("controllo se le ore effettuate sono uguali alle ore di studio totali per il corso in oggetto");
				if(Double.compare(Double.parseDouble(oreStudioTotale), Double.parseDouble(oreEffettuate)) <=0) {
					log.debug("oreEffettuate >= oreStudioTotale , quindi il corso è COMPLETATO");
					log.debug("setto l'attributo completed, del corso in oggetto al valore true");
					map2.getNamedItem("completed").setNodeValue("true");
					
				}

				node = node.getNextSibling();
			}
		}
		
//		log.debug("esegue quindi l'aggiornamento dell'attributo oreEffettuateTotali");
//		map.getNamedItem("oreEffettuateTotali").setNodeValue(oreEffettuateTotali.toString());
		ris = nodeToString(nodeRoot);
		return "<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n"+ ris;
	}
	
	private String nodeToString(Node node) {
		StringWriter sw = new StringWriter();
		try {
		 Transformer t = TransformerFactory.newInstance().newTransformer();
		 t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		 t.setOutputProperty(OutputKeys.INDENT, "yes");
		 t.transform(new DOMSource(node), new StreamResult(sw));
		} catch (TransformerException te) {
		 log.debug("nodeToString Transformer Exception");
		}
		return sw.toString();
		}
	
	
	public void doInsert(Integer salId, String doc) throws SQLException, ClassNotFoundException {
		
		
		Properties props = new Properties();
		try {
		    props.load(this.getClass().getResourceAsStream("hibernate.properties"));
		}catch(Exception e){
		    System.out.println("Error loading hibernate "+ "properties.");
		    e.printStackTrace();
		    System.exit(0);
		}

		String connUrl =  props.getProperty("hibernate.connection.url");
		String username = props.getProperty("hibernate.connection." + "username");
		String password = props.getProperty("hibernate.connection.password");
//		Class.forName("org.hibernate.dialect.Oracle10gDialect");
		Connection conn = DriverManager.getConnection(connUrl, username, password);
		
		OraclePreparedStatement stmt = 
			    (OraclePreparedStatement) conn.prepareStatement(
			        "update STERTWI.SAL set PIANIFICAZIONEXML = XMLType(?)  where id = '"+salId+"'");

			// the second argument is a string..
//			String poString = "<PO><PONO>200</PONO><PNAME>PO_2</PNAME></PO>";

			// now bind the string..
			stmt.setString(1,doc);
			stmt.execute();
			stmt.close();
	}
	public String getPianificazioneXmlStr(Integer salId) {
		String ris = null;
		Properties props = new Properties();
		try {
		    props.load(this.getClass().getResourceAsStream("hibernate.properties"));
		}
		catch(Exception e){
		    System.out.println("Error loading hibernate "+ "properties.");
		    e.printStackTrace();
		    System.exit(0);
		}

		String connUrl =  props.getProperty("hibernate.connection.url");
		String username = props.getProperty("hibernate.connection." + "username");
		String password = props.getProperty("hibernate.connection.password");
//		Class.forName("org.hibernate.dialect.Oracle10gDialect");
		try {
			Connection conn = DriverManager.getConnection(connUrl, username, password);

			OraclePreparedStatement stmt = (OraclePreparedStatement) conn.prepareStatement("select PIANIFICAZIONEXML from STERTWI.SAL S  WHERE ID ='" + salId +"'"); 
			ResultSet rset = stmt.executeQuery(); 
			OracleResultSet orset = (OracleResultSet) rset; 
			while(orset.next()) {
				// get the XMLType 
				XMLType poxml = XMLType.createXML(orset.getOPAQUE(1)); 
				// get the XMLDocument as a string... 
//				Document podoc = (Document)poxml.getDOM();
				ris = poxml.getStringVal();
			}
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ris;
		}
		
		return ris;
	}
	
	public Document getPianificazioneXmlDoc(Integer salId) {
		Document ris = null;
		Properties props = new Properties();
		try {
		    props.load(this.getClass().getResourceAsStream("hibernate.properties"));
		}
		catch(Exception e){
		    System.out.println("Error loading hibernate "+ "properties.");
		    e.printStackTrace();
		    System.exit(0);
		}

		String connUrl =  props.getProperty("hibernate.connection.url");
		String username = props.getProperty("hibernate.connection." + "username");
		String password = props.getProperty("hibernate.connection.password");
//		Class.forName("org.hibernate.dialect.Oracle10gDialect");
		try {
			Connection conn = DriverManager.getConnection(connUrl, username, password);

			OraclePreparedStatement stmt = (OraclePreparedStatement) conn.prepareStatement("select PIANIFICAZIONEXML from STERTWI.SAL S  WHERE ID ='" + salId +"'"); 
			ResultSet rset = stmt.executeQuery(); 
			OracleResultSet orset = (OracleResultSet) rset; 
			while(orset.next()) {
				// get the XMLType 
				XMLType poxml = XMLType.createXML(orset.getOPAQUE(1)); 
				// get the XMLDocument as a string... 
				ris = (Document)poxml.getDOM();
//				ris = poxml.getStringVal();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return ris;
		}
		
		return ris;
	}
	
}
