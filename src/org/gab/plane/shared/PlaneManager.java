/**
 * 
 */
package org.gab.plane.shared;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.gab.plane.server.*;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;




/**
 * @author Gabriele P.
 *
 */
public class PlaneManager {
	
	private static final Log log = LogFactory.getLog(PlaneManager.class);

	private final Session session = HibernateSessionFactory.currentSession();
	
	private Transaction tx = session.beginTransaction();
	
	protected Session getSession() {
		try {
			return HibernateSessionFactory.currentSession();
		} catch (Exception e) {
			log.error("Could not locate Session in JNDI", e);
			throw new IllegalStateException(
					"Could not locate Session in JNDI");
		}
	}
	
/**
 * 
 * inizio DAO classe Studente
 * 
 */
//	public List getAllStudenti() throws Exception {
//		
//		/* will hold the books we are going to return later */
//		List utenti = new ArrayList();
//		
//		
//		List tmpUtenti = session.createQuery("select b from Studente as b order by b.cognome, b.nome").list();
//		for (Iterator iter = tmpUtenti.iterator(); iter.hasNext();) {
//			utenti.add((Studente) iter.next());
//		}
//		tx.commit();
//		
//		closeSession();
//		
//		return utenti;
//		
//	}
//	
//	
//	public void saveStudente(Studente utente)  {
//		
//		if (utente.getId() == null || utente.getId().intValue() == 0) // [laliluna]
//			session.save(utente);
//		else {
//			Studente toBeUpdated = (Studente) session.get(Studente.class,utente.getId());
//			toBeUpdated.setNome(utente.getNome());
//			toBeUpdated.setCognome(utente.getCognome());
//			toBeUpdated.setPwd(utente.getPwd());
//			session.update(toBeUpdated);
//		}
//		tx.commit();
//
//	}
//	
//	/**
//	 * 
//	 * @param instance
//	 * @return Lista di tutti i record contenuti nel db associati all'istanza passata in input
//	 * @throws Exception
//	 * 
//	 * Ricerca tramite istanza
//	 */
//	public List findByExample(Studente instance) {
//		log.debug("finding Studente instance by example");
//		try {
//			List ris = (List) session.createCriteria("org.gab.plane.server.Studente").add(Example.create(instance)).list();
//			log.debug("find by example successful, result size: "+ ris.size());
//			return ris;
//		} catch (RuntimeException re) {
//			log.error("find by example failed", re);
//			throw re;
//		}
//	}
//	
//	/**
//	 * 
//	 * @param id
//	 * @return
//	 * @throws Exception
//	 * 
//	 * Ricerca tramite id
//	 */
//	public Studente findById(Integer id)  {
//		log.debug("getting Studente instance with id: " + id);
//		try {
//			Studente instance = (Studente) session.get("org.gab.plane.server.Studente", id);
//			if (instance == null) {
//				log.debug("get successful, no instance found");
//			} else {
//				log.debug("get successful, instance found");
//			}
//			return instance;
//		} catch (RuntimeException re) {
//			System.out.println("get failed");
//			log.error("get failed", re);
//			throw re;
//		}
//	}
//	
//	/**
//	 * 
//	 * @param transientInstance
//	 * 
//	 * Crea il record nel dbms
//	 */
//	public void persist(Studente transientInstance) {
//		
//		log.debug("persisting Studente instance");
//		try {
//			session.persist(transientInstance);
//			log.debug("persist successful");
//		} catch (RuntimeException re) {
//			log.error("persist failed", re);
//			throw re;
//		} 
//	}
//
//	/**
//	 * 
//	 * @param instance
//	 * 
//	 *  
//	 */
//	public void attachDirty(Studente instance) {
//		log.debug("attaching dirty Studente instance");
//		try {
//			session.saveOrUpdate(instance);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//
//	/**
//	 * 
//	 * @param instance
//	 * 
//	 * ???
//	 */
//	@SuppressWarnings("deprecation")
//	public void attachClean(Studente instance) {
//		
//		log.debug("attaching clean Studente instance");
//		try {
//			session.lock(instance, LockMode.NONE);
//			log.debug("attach successful");
//		} catch (RuntimeException re) {
//			log.error("attach failed", re);
//			throw re;
//		}
//	}
//	
//	/**
//	 * 
//	 * @param persistentInstance
//	 * 
//	 * elimina dal db l'istanza passata in input
//	 */
//	public void delete(Studente persistentInstance) {
//		log.debug("deleting Studente instance");
//		try {
//			session.delete(persistentInstance);
//			log.debug("delete successful");
//		} catch (RuntimeException re) {
//			log.error("delete failed", re);
//			throw re;
//		}
//	}
	
	
//	/**
//	 * 
//	 * @param detachedInstance
//	 * @return
//	 * 
//	 * Aggiorna il database con i dati memorizzati nell’entità
//	 */
//	public Studente merge(Studente detachedInstance) {
//		log.debug("merging Studente instance");
//		
//		try {
//			Studente result = (Studente) ((SessionFactory) session).getCurrentSession().merge(detachedInstance);
//			log.debug("merge successful");
//			return result;
//		} catch (RuntimeException re) {
//			log.error("merge failed", re);
//			throw re;
//		}
//	}
//	
/**
 * 
 * FINE DAO classe Studente
 * 
 */
	
/************************************************************************************************************/

/**
 * 
 * inizio DAO classe Piano
 * 
 */
//	public Piano[] getAllPiani() throws Exception {
//		
//		/* will hold the books we are going to return later */
//		List piani = new ArrayList();
//		
//		openSession();
//
//		
//		List tmpSPiani = session.createQuery(
//				"select b from Piano as b order by b.nome").list();
//		for (Iterator iter = tmpSPiani.iterator(); iter.hasNext();) {
//			piani.add((Piano) iter.next());
//		}
//		tx.commit();
//		
//		closeSession();
//		
//		return (Piano[]) piani.toArray(new Piano[0]);
//		
//		
//		
//	}
//	
//	public Piano getPianoByPrimaryKey(Integer primaryKey) throws Exception {
//		/* holds our return value */
//		Piano piano = null;
//		openSession();
//
//		piano = (Piano) session.get(Piano.class, primaryKey);
//		tx.commit();
//		closeSession();
//		
//		return piano;
//	}
//	
//	public Set<Piano> getSetPiano(Set<Piano> pianoValue) throws Exception{
//		/* will hold the books we are going to return later */
//		
//		
//		openSession();
//		
//		Set<Piano> piani = new HashSet<Piano>();
//
//		for (Iterator<Piano> iter = pianoValue.iterator(); iter.hasNext();) {
//			piani.add((Piano) iter.next());
//		}
//		tx.commit();
//		
//		closeSession();
//		
//		return piani;
//		
//	}
//	
//	public void savePiano(Piano piano) throws Exception {
//		openSession();
//		
//		if (piano.getId() == null || piano.getId().intValue() == 0) // [laliluna]
//			session.save(piano);
//		else {
//			Piano toBeUpdated = (Piano) session.get(Piano.class,piano.getId());
//			toBeUpdated.setNome(piano.getNome());
//			session.update(toBeUpdated);
//		}
//		tx.commit();
//		closeSession();
//	}
//
//	public void removePianoByPrimaryKey(Integer primaryKey) throws Exception {
//		openSession();
//		
//		Piano piano = (Piano) session.get(Piano.class, primaryKey);
//		if (piano != null) {
//			Set corsi = piano.getCorsi();
//			for (Iterator iter = corsi.iterator(); iter.hasNext();) {
//				Corso element = (Corso) iter.next();
//				element.setPiano(null);
//			}
//
//			session.delete(piano);
//		}
//		tx.commit();
//		closeSession();
//	}
//	
///**
// * 
// * FINE DAO classe Piano
// * 
// */
//
///************************************************************************************************************/
//
///**
// * 
// * inizio DAO classe Corso
// * 
// */
//	public Corso[] getAllCorsi() throws Exception {
//		
//		/* will hold the books we are going to return later */
//		List corsi = new ArrayList();
//		
//		openSession();
//
//		
//		List tmpSCorsi = session.createQuery(
//				"select b from Corso as b order by b.nome").list();
//		for (Iterator iter = tmpSCorsi.iterator(); iter.hasNext();) {
//			corsi.add((Corso) iter.next());
//		}
//		tx.commit();
//		
//		closeSession();
//		
//		return (Corso[]) corsi.toArray(new Corso[0]);
//
//	}
//	
//	public void saveCorso(Corso corsoValue) throws Exception {
//		openSession();
//		
//		Corso corso;
//		if (corsoValue.getId() != null && corsoValue.getId().intValue() != 0) { // [laliluna]
//			// 04.12.2004
//			// load
//			// book
//			// from
//			// DB
//			corso = (Corso) session.get(Corso.class, corsoValue.getId());
//			if (corso != null) {
//				corso.setNome(corsoValue.getNome());
//				corso.setArgomenti(corsoValue.getArgomenti());
//				session.update(corso);
//			}
//		} else // [laliluna] 04.12.2004 create new book
//		{
//			corso = new Corso();
//			corso.setNome(corsoValue.getNome());
//			corso.setArgomenti(corsoValue.getArgomenti());
//			session.save(corso);
//		}
//		tx.commit();
//		closeSession();
//	}
//	
//	
//	public Corso getCorsoByPrimaryKey(Integer primaryKey) throws Exception {
//		/* holds our return value */
//		Corso corso = null;
//		openSession();
//
//		corso = (Corso) session.get(Corso.class, primaryKey);
//		tx.commit();
//		closeSession();
//		
//		return corso;
//	}
//	
//	public void removeCorsoByPrimaryKey(Integer primaryKey) throws Exception {
//		
//		Corso corso = (Corso) session.get(Corso.class, primaryKey);
//		if (corso != null)
//			if (corso.getPiano()!= null)
//				corso.getPiano().getCorsi().remove(corso);
//		session.delete(corso);
//		tx.commit();
//		closeSession();
//	}
	
/**
 * 
 * FINE DAO classe Corso
 * 
 */

/************************************************************************************************************/
/************************************************************************************************************/
/************************************************************************************************************/
	
/**
 * 
 * inizio DAO classe shared
 * 
 */
	
	public List getByQuery(String query) {
		log.debug("select list by query sql free");
		
		try {
			List ris =  session.createQuery(query).list();
			return ris;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

/**
 * 
 * Fine DAO classe shared
 * 
 */
/************************************************************************************************************/
/************************************************************************************************************/
/************************************************************************************************************/
	

	public List findByExample(Object instance) {
		log.debug("finding Studente instance by example");
		List results = null;
		try {
			if(instance instanceof Studente) {
				log.debug("chiamo findByExample di Studente");
				StudenteHome sh = new StudenteHome();
				results = sh.findByExample((Studente)instance);
				return results;
			}
			else if(instance instanceof Piano) {
				log.debug("chiamo findByExample di Piano");
				PianoHome ph = new PianoHome();
				results = ph.findByExample((Piano)instance);
				return results;
			}
			else if(instance instanceof Corso) {
				log.debug("chiamo findByExample di Corso");
				CorsoHome ch = new CorsoHome();
				results = ch.findByExample((Corso)instance);
				return results;
			}
			else {
				log.error("input non rientra nelle tre istanze compatibili: Studente, Piano, Corso");
				return results;
			}
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public void commit() {
		tx.commit();
	}
	
	protected Session getSessionFactory()  {
		return HibernateSessionFactory.currentSession();
//		tx = session.beginTransaction();
	}
	
	protected void closeSession() throws Exception {
		HibernateSessionFactory.closeSession();
	}
}
