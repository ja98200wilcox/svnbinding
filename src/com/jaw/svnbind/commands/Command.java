package com.jaw.svnbind.commands;

public class Command {
	//private String bugzillaUserid;
	//private String bugzillaPassword;
	private String commandName;
	private String transactionId;
	private String revision;
	private String repoPath;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getRevision() {
		return revision;
	}
	public void setRevision(String revision) {
		this.revision = revision;
	}
	public String getCommandName() {
		return commandName;
	}
	public void setCommandName(String commandName) {
		this.commandName = commandName;
	}
	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}
	public String getRepoPath() {
		return repoPath;
	}
	
}
