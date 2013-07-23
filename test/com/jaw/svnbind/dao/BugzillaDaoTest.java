package com.jaw.svnbind.dao;

import static org.junit.Assert.*;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.dao.BugzillaDao;
import com.jaw.svnbind.guice.Module;

public class BugzillaDaoTest {

	@Test
	public void testAddComment() throws Exception {
		Injector injector = Guice.createInjector(new Module());
		BugzillaDao bugzillaDao = injector.getInstance(BugzillaDao.class);
		XmlRpcClient xmlRpcClient = bugzillaDao.login("jwilcox@localmatters.com", "alfred");
		bugzillaDao.addComment("test1", "1",xmlRpcClient);
	}
	@Test
	public void testLogin() throws Exception {
		Injector injector = Guice.createInjector(new Module());
		BugzillaDao bugzillaDao = injector.getInstance(BugzillaDao.class);
		bugzillaDao.login("jwilcox@localmatters.com", "alfred");
	}
}
