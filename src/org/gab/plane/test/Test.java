package org.gab.plane.test;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.gab.plane.server.*;
import org.gab.plane.shared.Pianificazione;
import org.gab.plane.shared.PlaneManager;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import com.google.appengine.labs.repackaged.com.google.common.io.Files;


public class Test {
	private Session session;

	//private static Logger log;
	private static Logger log = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		/*
		 * hibernate needs log4j. Either create a log4j.properties file in the
		 * source directory * or alternatively make the following to create a
		 * standard configuration BasicConfigurator.configure();
		 */
		Test Test = new Test();
		try {
			
			//log.info("setUP");
//			Test.setUp();
			//Test.addRel();
			//Test.testCreateDomains();
			//Test.testAddRemoveRelation();
			//Test.listCorsi();
//			Test.testComp();
//			Corso cornew = new Corso();
//			cornew.setNome("nome corso set");
//			cornew.setArgomenti("argooooooooo");
//			cornew.addArgomenti("aggiunta argo");
//			pl.saveCorso(cornew);
//			Studente[] st = pl.getAllStudenti();
//			Corso[] co = pl.getAllCorsi();
//			Piano[] pi = pl.getAllPiani();
			
//			PlaneManager pl = new PlaneManager();
//			List stud = pl.findByExample(new Studente());
			
//			log.setLevel(Level.DEBUG);
			Logger.getRootLogger().setLevel(Level.DEBUG);
			System.out.println(log.isDebugEnabled());
			
			CorsoHome ch = new CorsoHome();
			
			Corso corso1 = new Corso();
			corso1.setNome("Analisi");
			corso1.setCfu(12);
			corso1.setOreEffettuate(24);
			ch.attachDirty(corso1);
			
			Corso corso2 = new Corso();
			corso2.setNome("Geometria");
			corso2.setCfu(10);
			ch.attachDirty(corso2);
			
			ch.commit();
			
			
			PianoHome ph = new PianoHome();
			Piano p = new Piano();
			p.setOreStudioGiorno(5);
			p.getCorsi().add(corso1);
			p.getCorsi().add(corso2);
			ph.attachDirty(p);
			ph.commit();
			
			
			StudenteHome sth = new StudenteHome();
			Studente s = new Studente();
			sth.attachDirty(s);
			sth.commit();
			
			
//			SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
//			fmt.setLenient(false); 
//			Date d1 = (Date) fmt.parse("11/03/2013");
//			Date d2 = (Date) fmt.parse("15/04/2013");
			Timestamp startStudio = new Timestamp(0).valueOf("2013-03-10 00:00:00");
			Timestamp finishStudio = new Timestamp(0).valueOf("2015-4-15 00:00:00");
//			new Timestamp(yyyy-mm-dd hh:mm:ss);
			SalHome sah = new SalHome();
			Sal sal = new Sal();
			sal.setPiano(p);
			sal.setStudente(s);
			sal.setStartStudio(startStudio);
			sal.setFinishStudio(finishStudio);
			sah.attachDirty(sal);
			sah.commit();
			log.info(sal);
			Pianificazione pian = new Pianificazione(sal);
			pian.elaborazioneSal(sal);
			
			String xml = pian.getPianificazioneXmlStr(sal.getId());
			log.debug(xml);
			
//			pian.primaElaborazioneSal(sal);
//			
//			xml = pian.getPianificazioneXmlStr(sal.getId());
//			log.debug(xml);
			
//-----------------------------------------------------------------------			
//			Document doc = pian.getPianificazioneXmlDoc(sal.getId());
//			log.debug(doc);
//			
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource source = new DOMSource(doc.getLastChild());
//
//			log.debug("Creo file in directory C:/");
//			File fileXml = new File("c:/xmlToDb.xml");
//			StreamResult result = new StreamResult(fileXml);
//	 
//			transformer.transform(source, result);
//			
//	    	File folder = new File("src/xmlTemp");
//		    folder.mkdir();
//		    File f = new File(folder, "xmltoDb_" + System.currentTimeMillis() + ".xml");
//
//	        Files.copy(fileXml,f);
//	        log.debug("delete xml temp ---> " + fileXml.delete());
//			log.debug("File saved!");
//-----------------------------------------------------------------------			

			
			
			
			
//			pian.rielaborazioneSal(sal);
//			log.debug("debug creo planeManager");
//			PlaneManager pl = new PlaneManager();
//			
//			Studente s = new Studente();
//			s.setCognome("ccccc");
//			s.setMatricola("11sscccccc2");
//			log.info(pl.findByExample(s));
//			pl.persist(s);
////			pl.tx.commit();
//			pl.attachDirty(s);
//			pl.committ();
//			pl.attachClean(s);
//			pl.committ();
			
//			StudenteHome sh = new StudenteHome();
////			sh.persist(s);
//			sh.attachDirty(s);
//			sh.commit();
//			log.info(s);
//			
//			log.info("////");
//			log.info(sh.findById(s.getId()));
//			
//			List stud = pl.findByExample(new Studente());
//			
//			log.info("print all");
//			log.info(stud);
//			Studente studente1 = null;
//			log.info("print con iterator");
//			for (Iterator iter = stud.iterator(); iter.hasNext();) {
//				log.info(studente1 = (Studente) iter.next());
//				log.info(studente1.getId());
//			}
//			log.info("findById");
//			Studente studente2 = pl.findById(3);
//			log.info(studente2);
			
//			log.info("preQuery sql 1");
//			List l = pl.getByQuery("select b from Studente as b order by b.cognome, b.nome");
//			log.info("postQuery sql 1");
//			for (Iterator iter = l.iterator(); iter.hasNext();) {
//				log.info(iter.next());
//				
//			}
			
//			log.info(l);
//			log.info("query sql 2");
//			List ll = pl.getQuery();
//			log.info(ll);

			
//			for (Iterator iter = st.iterator(); iter.hasNext();) {
//				Studente stud = (Studente) iter.next();
//				System.out.println(stud.toString());
//			}
//			System.out.println(co);
//			System.out.println(pi);
			
			
//			for(int i=0;i<ut.length; i++) {
//				System.out.println("////");
//				System.out.println(ut[i].toString());
//				System.out.println("\\\\");
//			}
			
//			Test.tearDown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void testCreateDomains() {
		Transaction tx = session.beginTransaction();
		Piano piano = new Piano();
		piano.setNome("Piano in testCreateDomains");
		session.save(piano);
		tx.commit();
		
		tx = session.beginTransaction();
		Corso corso = new Corso();
		corso.setNome("Ingegneria");
		session.save(corso);
		tx.commit();
		
		
		
		
		

	}

	private void testAddRemoveRelation() {
		System.out.println("Adding and removing relations");
		Transaction tx = session.beginTransaction();


		// create two corsi and a piano
		Corso corso1 = new Corso();
		corso1.setNome("Analisi2");
		session.save(corso1);
		Corso corso2 = new Corso();
		corso2.setNome("Geometria2");
		session.save(corso2);

		Piano piano = new Piano();
		piano.setNome("Piano con SET<Corso>2");
		session.save(piano);
		
		//customer borrows  books
		piano.getCorsi().add(corso1);
		piano.getCorsi().add(corso2);
		session.flush();


		session.refresh(piano);
		session.refresh(corso1);
		session.refresh(corso2);
		Set<Corso> corsi = piano.getCorsi();
		
		System.out.println("list books of customer");
		for (Iterator<Corso> iter = corsi.iterator(); iter.hasNext();) {
			Corso element = (Corso) iter.next();
			System.out.println(element);
		}
		tx.commit();
		
//		//first book is returned
//		corso1.setPiano(null);
//		piano.getCorsi().remove(corso1);
//		session.flush();
//		session.refresh(piano);
//		System.out.println("list books of customer");
//		corsi = piano.getCorsi();
//		for (Iterator iter = corsi.iterator(); iter.hasNext();) {
//			Corso element = (Corso) iter.next();
//			System.out.println(element);
//		}
//
//		tx = session.beginTransaction();
//		session.delete(piano);
//		session.delete(corso1);
//		session.delete(corso2);
//		tx.commit();
//		System.out.println("Fine DELETED------------------------");

	}
	
	public void testComp() {
		System.out.println("Inizio creazione sessione");
		Transaction tx = session.beginTransaction();
		
		
		System.out.println("Creo Studente");
		Studente s1 = new Studente("a","b","c","d");
		session.save(s1);
		session.flush();
		
		System.out.println("Creo Piano");
		Piano p1 = new Piano();
		p1.setNome("Nome Piano");
		//p1.setStudente(s1);
		session.save(p1);
		session.flush();
		
		System.out.println("Aggiungo Piano a Studente - join");
		s1.getPiano().add(p1);
		session.flush();
		
		System.out.println("Creo due Corsi");
		Corso corso1 = new Corso();
		corso1.setNome("Corso1");
		corso1.addArgomenti("argo1");
		corso1.addArgomenti("argo2");
		session.save(corso1);
		Corso corso2 = new Corso();
		corso2.setNome("Corso2");
		session.save(corso2);
		session.flush();
		
		
		System.out.println("Aggiungo i due corsi al piano - join");
		p1.getCorsi().add(corso1);
		p1.getCorsi().add(corso2);
		session.flush();
		
		tx.commit();
		
		System.out.println("Studente : " + s1);

	}

	public void setUp() throws Exception {
		session = HibernateSessionFactory.currentSession();
		//log = Logger.getLogger(this.getClass());
	}

	public void tearDown() throws Exception {
		HibernateSessionFactory.closeSession();
	}

	/**
	 * creates a book and saves it to the db.
	 * 
	 */

	/**
	 * lists all books in the db
	 * 
	 */
	private void listCorsi() {
		System.out.println("####### list corsi");

		Transaction tx = session.beginTransaction();
		
//		List corsi2 = session.createSQLQuery("select * from Corso").list();
//		for (Iterator iter = corsi2.iterator(); iter.hasNext();) {
//			Corso element2 = (Corso) iter.next();
//			System.out.println(element2);
//		}
		//tx.commit();
		
		List piano = session.createQuery("select c from Piano as c").list();
		for (Iterator iter = piano.iterator(); iter.hasNext();) {
			Piano element = (Piano) iter.next();
			Set<Corso> corsi = element.getCorsi();
			System.out.println(element);
			System.out.println(corsi);
			
			if (corsi == null)
				System.out.println("no books");
			else {
				for (Iterator iterator = corsi.iterator(); iterator.hasNext();) {
					Corso corso = (Corso) iterator.next();
					System.out.println("ID corso: " + corso.getId());
					System.out.println("NOME corso: " + corso.getNome());
					System.out.println("PIANO corso: " + corso.getPiano().getNome());
				}
			}
		}
		List idPiano = session.createQuery("select c.id from Piano as c").list();
		for (Iterator iter2 = idPiano.iterator(); iter2.hasNext();) {
			Integer element2 = (Integer) iter2.next();
			System.out.println(element2);
		}
		

	}

	/**
	 * @return Returns the session.
	 */
	public Session getSession() {
		return session;
	}

	/**
	 * @param session
	 *            The session to set.
	 */
	public void setSession(Session session) {
		this.session = session;
	}
	
}
