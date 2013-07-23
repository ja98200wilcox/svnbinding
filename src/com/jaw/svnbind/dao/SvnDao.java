package com.jaw.svnbind.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.PumpStreamHandler;

import com.google.inject.Inject;
import com.jaw.svnbind.Application;
import com.jaw.svnbind.ConfigurationManager;

public class SvnDao {
	private static Logger logger = Logger.getLogger(SvnDao.class
			.getName());
	private String svnlook;
	@Inject
	public SvnDao(ConfigurationManager configurationManager) {
		svnlook = configurationManager.getConfig().getString("svnlook");
	}

	private String execute(String line) throws ExecuteException, IOException {
		
		ByteArrayOutputStream stdout = new ByteArrayOutputStream();
		try{
		PumpStreamHandler psh = new PumpStreamHandler(stdout);
		CommandLine cl = CommandLine.parse(line);
		DefaultExecutor exec = new DefaultExecutor();
		exec.setStreamHandler(psh);
		exec.execute(cl);
		}catch(Exception e){
			logger.log(Level.SEVERE, e.getMessage(), e);
			logger.info(line);
		}
		return stdout.toString();
	}

	public String getSvnUserViaTrans(String respository, String transactionId)
			throws ExecuteException, IOException {
		String line = svnlook +" author -t " + transactionId + " " + respository;		
		return execute(line);
	}

	public String getSvnUserViaRev(String respository, String revisionId)
			throws ExecuteException, IOException {
		String line = svnlook +" author -r " + revisionId + " " + respository;
		return execute(line);
	}

	public String getMessageViaTrans(String respository, String transactionId)
			throws ExecuteException, IOException {
		String line = svnlook +" log -t " + transactionId + " " + respository;
		return execute(line);
	}

	public String getMessageViaRev(String respository, String revisionId)
			throws ExecuteException, IOException {
		String line = svnlook +" log -r " + revisionId + " " + respository;
		return execute(line);
	}

	public String getChanged(String respository, String revisionId)
			throws ExecuteException, IOException {
		String line = svnlook +" changed -r " + revisionId + " " + respository;
		return execute(line);
	}

}
