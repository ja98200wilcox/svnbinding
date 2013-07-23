svnbinding
==========

Bind svn with bugzilla.  

When users check in code they are required to provide a bug number. 
Bugzilla is then updated with all the info from the svn checkin.

For Bugzilla you need to turn on xmlrpc interface.  Check bugzilla doc on how too.

HOW TO USE
------------
Grab the dist directory for the lastest build.  Everything is in the dist directory.

Important things in the dist:

-- Two hooks to be installed on svn.  They are in the dist/hooks directory.  They are pre and post commit scripts.

-- The file Config.xml.  This is the configuration file for svnbind.

  In the Config.xml you will need too:
    -- set the bugzillaEndpoint tobugzilla xmlrpc interface.
    -- set svnlook, which is an svn command.
    -- set the users that will be checking in code.  
    You have to encypt the bugzilla passwords.  Later i will show you how.
      
      For example:
       <users><!-- Bugzilla users that will do svn checkins -->
          <TestUser1> <!-- SVN username -->
        		<bugzillaUserId>TestUser1@foo.com</bugzillaUserId> <!-- bugzilla username -->
        		<bugzillaPassword>encrpyted password here</bugzillaPassword> <!-- bugzilla encrpted password -->
        	</TestUser1>
        </users>
    
    HOW TO ENCRPT PASSWORDS
    -----------------------
    1) Open a command propt or shell
    2) go to svnbind directory where svnbind-1.1.jar is located.
    3) type in java -jar svnbind-1.0.jar -cmd encrypt -pwd thepasswordtoencrpyt
      
      For Example:
      
      C:\vob\svnbind\project\dist>java -jar svnbind-1.0.jar -cmd encrypt -pwd hasdhas
      Output:
      Encrypted Password: oiRUqYbgCBGC9N97r5sT4Q==
      
      
      Cut and paste oiRUqYbgCBGC9N97r5sT4Q== and add it to  <bugzillaPassword>
  
    
    





