package org.gab.plane.server;

// Generated 8-mar-2013 15.59.48 by Hibernate Tools 4.0.0

import java.sql.Timestamp;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.gab.plane.util.HibernateSessionFactory;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class Sal.
 * @see org.gab.plane.server.Sal
 * @author Hibernate Tools
 */
public class SalHome {
	
	private static final Log log = LogFactory.getLog(SalHome.class);

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
	
	public Session getSessionPublic() {
		try {
			return HibernateSessionFactory.currentSession();
		} catch (Exception e) {
			log.error("Could not locate Session in JNDI", e);
			throw new IllegalStateException(
					"Could not locate Session in JNDI");
		}
	}
	

	public void persist(Sal transientInstance) {
		log.debug("persisting Sal instance");
		try {
			session.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Sal instance) {
		log.debug("attaching dirty Sal instance");
		try {
			session.saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sal instance) {
		log.debug("attaching clean Sal instance");
		try {
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Sal persistentInstance) {
		log.debug("deleting Sal instance");
		try {
			session.delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sal merge(Sal detachedInstance) {
		log.debug("merging Sal instance");
		try {
			Sal result = (Sal) session.merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Sal findById(Integer id) {
		log.debug("getting Sal instance with id: " + id);
		try {
			Sal instance = (Sal) session.get(
					"org.gab.plane.server.Sal", id);
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

	public List findByExample(Sal instance) {
		log.debug("finding Sal instance by example");
		try {
			List results = session
					.createCriteria("org.gab.plane.server.Sal")
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
