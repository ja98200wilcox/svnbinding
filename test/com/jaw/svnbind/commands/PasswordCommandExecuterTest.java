package com.jaw.svnbind.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.commands.PasswordCommandExecuter;
import com.jaw.svnbind.guice.Module;

public class PasswordCommandExecuterTest {

	@Test
	public void testEncrypt() {
		Injector injector = Guice.createInjector(new Module());
		PasswordCommandExecuter passwordCommandExecuter = injector
				.getInstance(PasswordCommandExecuter.class);
		String password = "Jimmy";
		String encrptied = passwordCommandExecuter.encrypt(password);
		String passwordDecrypted = passwordCommandExecuter.decrypt(encrptied);
		assertEquals("grrr",password,passwordDecrypted);
	}

	
}
