package org.gab.plane.server;

// Generated 21-feb-2013 11.40.46 by Hibernate Tools 4.0.0

import java.util.List;
import javax.naming.InitialContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Corso.
 * @see org.gab.plane.server.Corso
 * @author Hibernate Tools
 */
public class CorsoHome {

	private static final Log log = LogFactory.getLog(CorsoHome.class);

	private final Session session = getSession();
	
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

	public void persist(Corso transientInstance) {
		log.debug("persisting Corso instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Corso instance) {
		log.debug("attaching dirty Corso instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Corso instance) {
		log.debug("attaching clean Corso instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Corso persistentInstance) {
		log.debug("deleting Corso instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Corso merge(Corso detachedInstance) {
		log.debug("merging Corso instance");
		try {
			Corso result = (Corso) session.merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Corso findById(java.lang.Integer id) {
		log.debug("getting Corso instance with id: " + id);
		try {
			Corso instance = (Corso) session.get(
					"org.gab.plane.server.Corso", id);
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

	public List findByExample(Corso instance) {
		log.debug("finding Corso instance by example");
		try {
			List results = session.createCriteria("org.gab.plane.server.Corso")
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
