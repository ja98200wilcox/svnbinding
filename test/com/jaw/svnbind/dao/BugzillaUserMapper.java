package com.jaw.svnbind.dao;

import com.google.inject.Inject;
import com.jaw.svnbind.ConfigurationManager;

public class BugzillaUserMapper {
	private ConfigurationManager configurationManager;
	@Inject
	public BugzillaUserMapper(ConfigurationManager configurationManager){
		this.configurationManager = configurationManager;
	}
	
	
}
