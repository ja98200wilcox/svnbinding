package com.jaw.svnbind;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.guice.Module;

public class Application {
	private static Logger logger = Logger.getLogger(Application.class
			.getName());

	/**
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args){
		
		try{
			Injector injector = Guice.createInjector(new Module());
			Handler handler = injector.getInstance(Handler.class);
			handler.dispatch(args);
		}catch (Exception e) {
			logger.info("Done exit 1");
			logger.severe(e.getMessage());
			System.exit(1);
		}	
		logger.info("Done exit 0");
		System.exit(0);
	}
}
