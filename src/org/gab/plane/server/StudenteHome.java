package org.gab.plane.server;

// Generated 21-feb-2013 11.40.46 by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Studente.
 * @see org.gab.plane.server.Studente
 * @author Hibernate Tools
 */
public class StudenteHome {

	private static final Log log = LogFactory.getLog(StudenteHome.class);

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
	 * @param transientInstance
	 * 
	 * Crea il record nel db
	 */
	public void persist(Studente transientInstance) {
		log.debug("persisting Studente instance");
		try {
//			HibernateSessionFactory.currentSession().persist(transientInstance);
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param instance
	 * 
	 * Update dell'istanza nel db 
	 */
	public void attachDirty(Studente instance) {
		log.debug("attaching dirty Studente instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param instance
	 * 
	 * ???
	 */
	@SuppressWarnings("deprecation")
	public void attachClean(Studente instance) {
		log.debug("attaching clean Studente instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param persistentInstance
	 * 
	 * Elimina dal db il record corrispondente all'istanza passata in input
	 */
	public void delete(Studente persistentInstance) {
		log.debug("deleting Studente instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	
	/**
	 * 
	 * @param detachedInstance
	 * @return
	 * 
	 * Aggiorna il database con i dati memorizzati nell’entità
	 */
	public Studente merge(Studente detachedInstance) {
		log.debug("merging Studente instance");
		try {
			Studente result = (Studente) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Studente findById(java.lang.Integer id) {
		log.debug("getting Studente instance with id: " + id);
		try {
			Studente instance = (Studente) session.get("org.gab.plane.server.Studente", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Studente instance) {
		log.debug("finding Studente instance by example");
		try {
			List results = session.createCriteria("org.gab.plane.server.Studente")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	public void commit() {
		tx.commit();
	}
	
	public void closeSession() throws Exception {
		HibernateSessionFactory.closeSession();
	}
}
