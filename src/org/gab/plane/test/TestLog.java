package org.gab.plane.test;

import org.apache.log4j.*;

public class TestLog {

//	private static Logger log;
	//static Category cat = Category.getRoot();
//	static Category cat = Category.getInstance(TestLog.class.getName());
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Logger logger = Logger.getRootLogger();
		 // printing methods:
	      logger.info("Prova info!");
	      logger.debug("Prova debug!");
	      logger.warn("prova warn!"); 
	      logger.error("prova error!");
	      logger.fatal("prova fatal!");

	}
}
