package com.jaw.svnbind.commands;

import org.jasypt.util.text.BasicTextEncryptor;

import com.google.inject.Inject;

public class PasswordCommandExecuter {
	private String myEncryptionPassword = "kwjdcj3sfde54";
	private BasicTextEncryptor textEncryptor;
	@Inject
	public PasswordCommandExecuter(){
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword(myEncryptionPassword);
				
	}
	public String encrypt(String password){
		return textEncryptor.encrypt(password);
		
	}
	public String decrypt(String password){
		return textEncryptor.decrypt(password);
	}
	
}
