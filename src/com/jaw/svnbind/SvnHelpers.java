package com.jaw.svnbind;

import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.google.inject.Inject;

public class SvnHelpers {
	private static Logger logger = Logger.getLogger(SvnHelpers.class
			.getName());

	// private String bugRegex = "^bug\s";
	@Inject
	public SvnHelpers() {

	}

	public String getBugId(String message) throws Exception{
		
		String bugId= null;
		try{
		Pattern bugPattern = Pattern.compile("^bug\\s", Pattern.CASE_INSENSITIVE);
		String[] items = bugPattern.split(message);
		items = items[1].split("\\s");
		if(items.length < 2){
			throw new Exception();
		}
		bugId = items[0];
		if(!bugId.matches("^(\\d)?(\\d)*")){
			throw new Exception();
		}
		}catch(Exception e){
			throw new Exception("Message syntax error. Must be Bug [bugid] [message]");
		}
		return bugId;
	}
}
