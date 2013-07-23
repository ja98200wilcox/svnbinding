package com.jaw.svnbind;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

import com.google.inject.Inject;

public class ConfigurationManager {
	private static Logger logger = Logger.getLogger(ConfigurationManager.class
			.getName());

	private Configuration config;
	@Inject
	public ConfigurationManager() throws ConfigurationException{
		try{
		config = new XMLConfiguration("config.xml");
		}catch(ConfigurationException e){
			logger.log(Level.SEVERE,"Config.xml missing or corrupted");
			throw e;
		}
	}
	public Configuration getConfig() {
		return config;
	}	
	
}
