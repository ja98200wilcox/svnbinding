package com.jaw.svnbind;

import static org.junit.Assert.*;

import java.util.logging.LogManager;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.ConfigurationManager;
import com.jaw.svnbind.guice.Module;


public class ConfigurationManagerTest {

	@Test
	public void testGetConfig() {		
		Injector injector = Guice.createInjector(new Module());
		ConfigurationManager configurationManager = injector
				.getInstance(ConfigurationManager.class);
		String bugzillaEndpoint = configurationManager.getConfig().getString(
				"bugzillaEndpoint");
		assertNotNull(bugzillaEndpoint);
	}

}
