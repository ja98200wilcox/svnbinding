package com.jaw.svnbind;

import static org.junit.Assert.*;

import java.net.MalformedURLException;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.jaw.svnbind.Handler;
import com.jaw.svnbind.guice.Module;

public class HandlerTest {

	@Test
	public void testDispatchAddcomment() throws Exception {
		Injector injector = Guice.createInjector(new Module());
		Handler handler = injector.getInstance(Handler.class);
		String[] args = { "-cmd","comment", "-r","1","-repo", "/home/svn" };
		handler.dispatch(args);
	}
	@Test
	public void testDispatchVerify() throws Exception {
		Injector injector = Guice.createInjector(new Module());
		Handler handler = injector.getInstance(Handler.class);
		String[] args = { "-cmd","verify", "-t","1","-repo", "/home/svn" };
		handler.dispatch(args);
	}
	@Test
	public void testDispatchHelp() {
		Injector injector = Guice.createInjector(new Module());
		Handler handler = injector.getInstance(Handler.class);
		String[] args = { "help" };
		//handler.dispatch(args);
	}
}
