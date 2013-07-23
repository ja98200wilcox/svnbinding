package com.jaw.svnbind;

import java.util.logging.Logger;
import com.google.inject.Inject;
import com.jaw.svnbind.commands.Command;
import com.jaw.svnbind.commands.CommentCommandExecuter;
import com.jaw.svnbind.commands.PasswordCommandExecuter;
import com.jaw.svnbind.commands.VerifyCommandExecuter;

public class Handler {
	private static Logger logger = Logger.getLogger(Handler.class.getName());

	private static final String VERIFY = "verify";
	private static final String COMMIT = "comment";
	private static final String ENCRYPT = "encrypt";	
	private ParseArgs parseArgs;
	private VerifyCommandExecuter verifyCommandExecuter;
	private CommentCommandExecuter commentCommandExecuter;
	private PasswordCommandExecuter passwordCommandExecuter;

	@Inject
	public Handler( ParseArgs parseArgs, VerifyCommandExecuter verifyCommandExecuter, CommentCommandExecuter commentCommandExecuter, PasswordCommandExecuter passwordCommandExecuter) {
		this.parseArgs = parseArgs;
		this.verifyCommandExecuter = verifyCommandExecuter;
		this.commentCommandExecuter = commentCommandExecuter;
		this.passwordCommandExecuter = passwordCommandExecuter;
	}

	public void dispatch(String[] args) throws Exception {
		if(args == null){
			throw new Exception("Zero args");
		}
		Command command = parseArgs.dpParse(args);

		if (command.getCommandName().equalsIgnoreCase(VERIFY)) {
			//logger.info("Process verify");
			String transactionId = command.getTransactionId();
			if (transactionId == null || transactionId.length() == 0) {
				throw new Exception(
						"For "
								+ VERIFY
								+ " required field tranasactionId is missing. Did you forget to specify the -t with a transaction ID.");
			}
			String repoPath = command.getRepoPath();
			if (repoPath == null || repoPath.length() == 0) {
				throw new Exception(
						"For "
								+ VERIFY
								+ " required field repository path is missing. Did you forget to specify the -repo with a repository path.");
			}
			verifyCommandExecuter.execute(command);
		} else if (command.getCommandName().equalsIgnoreCase(COMMIT)) {
			//logger.info("Process commit");
			String revision = command.getRevision();
			if (revision == null || revision.length() == 0) {
				throw new Exception(
						"For "
								+ COMMIT
								+ " required field revision is missing. Did you forget to specify the -r with a revision.");
			}
			String repoPath = command.getRepoPath();
			if (repoPath == null || repoPath.length() == 0) {
				throw new Exception(
						"For "
								+ COMMIT
								+ " required field repository path is missing. Did you forget to specify the -repo with a repository path.");
			}
			commentCommandExecuter.execute(command);
		} else if(command.getCommandName().equalsIgnoreCase(ENCRYPT)) {
			logger.info("Encrypted Password: "+passwordCommandExecuter.encrypt(command.getPassword()));
		} else {
			throw new Exception("Commands supported: [" + VERIFY +"] [" +  COMMIT +"] ["+ ENCRYPT + "]");
		}
		
	}
	
}
