package com.jaw.svnbind.dao;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpState;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;

import com.google.inject.Inject;
import com.jaw.svnbind.ConfigurationManager;

public class BugzillaDao {
	private static Logger logger = Logger
			.getLogger(BugzillaDao.class.getName());
	private String bugzillaEndpoint;

	@Inject
	public BugzillaDao(ConfigurationManager configManager) {
		bugzillaEndpoint = configManager.getConfig().getString(
				"bugzillaEndpoint");
	}
	
	public XmlRpcClient login(String userid, String password) throws Exception {
		XmlRpcClient client = null;
		try {
			XmlRpcClientConfigImpl xmlRpcClientConfigImpl = new XmlRpcClientConfigImpl();
			xmlRpcClientConfigImpl.setServerURL(new URL(bugzillaEndpoint));
			client = new XmlRpcClient();
			client.setConfig(xmlRpcClientConfigImpl);
			XmlRpcCommonsTransportFactory xmlRpcCommonsTransportFactory = new XmlRpcCommonsTransportFactory(client);
			HttpClient pHttpClient = new HttpClient();
			xmlRpcCommonsTransportFactory.setHttpClient(pHttpClient);	
			client.setTransportFactory(xmlRpcCommonsTransportFactory);
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("login", userid);
			params.put("password", password);
			
			HashMap loginResult = (HashMap) client.execute("User.login",
					new Object[] { params });
			HttpState state = pHttpClient.getState();
			Cookie[] cookies = state.getCookies();
		} catch (MalformedURLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		} catch (XmlRpcException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
		return client;
	}

	public void addComment(String msg, String bugID,XmlRpcClient client) throws XmlRpcException {
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("id", bugID);
			params.put("comment", msg);
			HashMap commentResult = (HashMap) client.execute("Bug.add_comment",
					new Object[] { params });
		} catch (XmlRpcException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
	public void logout(XmlRpcClient client) throws XmlRpcException {
		try {
			HashMap<String, String> params = new HashMap<String, String>();
			client.execute("User.logout",
					new Object[] { params });
		} catch (XmlRpcException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw e;
		}
	}
	public boolean bugIdExistsAndOpen(String bugID, XmlRpcClient client) throws XmlRpcException{
		boolean result = false;
		try {
			HashMap<String, Integer[]> params = new HashMap<String, Integer[]>();
			int bugIdAsInt = Integer.parseInt(bugID);
			params.put("ids", new Integer[]{bugIdAsInt});
			HashMap response = (HashMap)client.execute("Bug.get_bugs",
					new Object[] { params });
			HashMap bugs = (HashMap)((Object[])response.get("bugs"))[0];
			result = ((Boolean)bugs.get("is_open")).booleanValue();			
		} catch (XmlRpcException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);		
		}
		return result;
	}
}
