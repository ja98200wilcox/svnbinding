package com.jaw.svnbind;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.SvnHelpers;
import com.jaw.svnbind.guice.Module;

public class SvnHelpersTest {

	@Test
	public void testGetBugId() throws Exception {
		Injector injector = Guice.createInjector(new Module());
		SvnHelpers svnHelpers = injector.getInstance(SvnHelpers.class);
		try {
			String result = svnHelpers.getBugId("bug 43");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("bug 43 dfs");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("gfgfd");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("bug gfgfd");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("gfgfd bug");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("bug 23");
		} catch (Exception e) {
			e.getMessage();
		}
		try {
			String result = svnHelpers.getBugId("bug");
		} catch (Exception e) {
			e.getMessage();
		}
		
	}

}
