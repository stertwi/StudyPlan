package org.gab.plane.server;

// Generated 21-feb-2013 11.40.46 by Hibernate Tools 4.0.0

import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Piano.
 * @see org.gab.plane.server.Piano
 * @author Hibernate Tools
 */
public class PianoHome {

	private static final Log log = LogFactory.getLog(PianoHome.class);

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

	public void persist(Piano transientInstance) {
		log.debug("persisting Piano instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Piano instance) {
		log.debug("attaching dirty Piano instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Piano instance) {
		log.debug("attaching clean Piano instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Piano persistentInstance) {
		log.debug("deleting Piano instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Piano merge(Piano detachedInstance) {
		log.debug("merging Piano instance");
		try {
			Piano result = (Piano) session.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Piano findById(java.lang.Integer id) {
		log.debug("getting Piano instance with id: " + id);
		try {
			Piano instance = (Piano) session.get("org.gab.plane.server.Piano", id);
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

	public List findByExample(Piano instance) {
		log.debug("finding Piano instance by example");
		try {
			List results = session.createCriteria("org.gab.plane.server.Piano")
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
