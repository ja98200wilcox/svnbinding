package com.jaw.svnbind;

import com.google.inject.Inject;
import com.jaw.svnbind.commands.Command;

public class ParseArgs {

	@Inject
	public ParseArgs() {

	}

	public Command dpParse(String[] args) throws Exception {
		Command command = new Command();
		for (int i = 0; i < args.length; i++) {
			if (args[i].startsWith("-")) {
				if (args[i].equals("-cmd")) {
					command.setCommandName(args[++i]);
				} else if (args[i].equals("-t")) {
					command.setTransactionId(args[++i]);
				} else if (args[i].equals("-r")) {
					command.setRevision(args[++i]);
				} else if (args[i].equals("-repo")) {
					command.setRepoPath(args[++i]);
				} else if (args[i].equals("-pwd")) {
					command.setPassword(args[++i]);
				} else {
					throw new Exception(
							"Incorrect syntax. Possible arguements [-cmd][-t][-r][-repo][-pwd]");
				}
			}
		}
		return command;
	}
}
