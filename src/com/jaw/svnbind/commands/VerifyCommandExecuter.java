package com.jaw.svnbind.commands;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.exec.ExecuteException;
import org.apache.xmlrpc.client.XmlRpcClient;

import com.google.inject.Inject;
import com.jaw.svnbind.ConfigurationManager;
import com.jaw.svnbind.Handler;
import com.jaw.svnbind.SvnHelpers;
import com.jaw.svnbind.dao.BugzillaDao;
import com.jaw.svnbind.dao.SvnDao;

public class VerifyCommandExecuter {
	private static Logger logger = Logger.getLogger(VerifyCommandExecuter.class.getName());
	private ConfigurationManager configurationManager;
	private BugzillaDao bugzillaDao;
	private SvnDao svnDao;
	private SvnHelpers svnHelpers;
	private PasswordCommandExecuter passwordCommandExecuter;

	@Inject
	public VerifyCommandExecuter(ConfigurationManager configurationManager,
			BugzillaDao bugzillaDao, SvnDao svnDao, SvnHelpers svnHelpers,PasswordCommandExecuter passwordCommandExecuter) {
		this.bugzillaDao = bugzillaDao;
		this.configurationManager = configurationManager;
		this.svnDao = svnDao;
		this.svnHelpers = svnHelpers;
		this.passwordCommandExecuter = passwordCommandExecuter;
	}

	public void execute(Command command) throws Exception {
		// /get name of svn user and convert to bugzilla
		String svnUser = null;
		try {
			svnUser = svnDao.getSvnUserViaTrans(command.getRepoPath(),
					command.getTransactionId()).trim();
			//logger.info("svnuser = " +svnUser);
			if (svnUser == null) {
				throw new Exception("Unable to find svn user.");
			}
		} catch (Exception e) {
			throw new Exception("Unable to find svn user.");
		}
		
		String bugzillaUser = configurationManager.getConfig().getString(
				"users." + svnUser + ".bugzillaUserId");
		String bugzillaPassword = configurationManager.getConfig().getString(
				"users." + svnUser + ".bugzillaPassword");
		bugzillaPassword = passwordCommandExecuter.decrypt(bugzillaPassword);
		// get subversion message and parse message looking for the bug id
		String svnMessage = null;
		try {
			svnMessage = svnDao.getMessageViaTrans(command.getRepoPath(),
					command.getTransactionId());
		} catch (Exception e) {
			throw new Exception("Unable to determine svn message.");
		}
		//logger.info("svnMessage = " + svnMessage);
		String bugId = svnHelpers.getBugId(svnMessage);
		// call bugzilla
		XmlRpcClient client = null;
		try {
			client = bugzillaDao.login(bugzillaUser, bugzillaPassword);
		} catch (Exception e) {
			throw new Exception("Unable to login to bugzilla. Using "
					+ bugzillaUser + " " + bugzillaPassword);
		}
		if (!bugzillaDao.bugIdExistsAndOpen(bugId, client)) {
			throw new Exception("Bug does not exist or is not open.");
		}
		bugzillaDao.logout(client);

	}
}
