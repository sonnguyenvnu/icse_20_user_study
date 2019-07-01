/** 
 * Check mail for a particular service that has registered with the mail transport
 * @param entry        the poll table entry that stores service specific informaiton
 * @param emailAddress the email address checked
 */
private void _XXXXX_(final PollTableEntry entry,InternetAddress emailAddress){
  if (log.isDebugEnabled()) {
    log.debug("Checking mail for account : " + emailAddress);
  }
  boolean connected=false;
  int retryCount=0;
  int maxRetryCount=entry.getMaxRetryCount();
  long reconnectionTimeout=entry.getReconnectTimeout();
  Session session=entry.getSession();
  Store store=null;
  Folder folder=null;
  boolean mailProcessingStarted=false;
  while (!connected) {
    try {
      retryCount++;
      if (log.isDebugEnabled()) {
        log.debug("Attempting to connect to POP3/IMAP server for : " + entry.getEmailAddress() + " using "+ session.getProperties());
      }
      store=session.getStore(entry.getProtocol());
      if (entry.getUserName() != null && entry.getPassword() != null) {
        store.connect(entry.getUserName(),entry.getPassword());
      }
 else {
        handleException("Unable to locate username and password for mail login",null);
      }
      connected=store.isConnected();
      if (connected) {
        if (entry.getFolder() != null) {
          folder=store.getFolder(entry.getFolder());
        }
 else {
          folder=store.getFolder(MailConstants.DEFAULT_FOLDER);
        }
        if (folder == null) {
          folder=store.getDefaultFolder();
        }
      }
    }
 catch (    Exception e) {
      log.error("Error connecting to mail server for address : " + emailAddress,e);
      if (maxRetryCount <= retryCount) {
        processFailure("Error connecting to mail server for address : " + emailAddress + " :: "+ e.getMessage(),e,entry);
        return;
      }
    }
    if (!connected) {
      try {
        log.warn("Connection to mail server for account : " + entry.getEmailAddress() + " failed. Retrying in : "+ reconnectionTimeout / 1000 + " seconds");
        Thread.sleep(reconnectionTimeout);
      }
 catch (      InterruptedException ignore) {
      }
    }
  }
  if (connected && folder != null) {
    CountDownLatch latch=null;
    Runnable onCompletion=new MailCheckCompletionTask(folder,store,emailAddress,entry);
    try {
      if (log.isDebugEnabled()) {
        log.debug("Connecting to folder : " + folder.getName() + " of email account : "+ emailAddress);
      }
      folder.open(Folder.READ_WRITE);
      int total=folder.getMessageCount();
      Message[] messages=folder.getMessages();
      if (log.isDebugEnabled()) {
        log.debug(messages.length + " messgaes in folder : " + folder);
      }
      latch=new CountDownLatch(total);
      for (int i=0; i < total; i++) {
        try {
          String[] status=messages[i].getHeader("Status");
          if (status != null && status.length == 1 && status[0].equals("RO")) {
            if (log.isDebugEnabled()) {
              log.debug("Skipping message #: " + messages[i].getMessageNumber() + " : "+ messages[i].getSubject()+ " - Status: RO");
            }
            latch.countDown();
          }
 else           if (messages[i].isSet(Flags.Flag.SEEN)) {
            if (log.isDebugEnabled()) {
              log.debug("Skipping message #: " + messages[i].getMessageNumber() + " : "+ messages[i].getSubject()+ " - already marked SEEN");
            }
            latch.countDown();
          }
 else           if (messages[i].isSet(Flags.Flag.DELETED)) {
            if (log.isDebugEnabled()) {
              log.debug("Skipping message #: " + messages[i].getMessageNumber() + " : "+ messages[i].getSubject()+ " - already marked DELETED");
            }
            latch.countDown();
          }
 else {
            processMail(entry,folder,store,messages[i],latch,onCompletion);
            mailProcessingStarted=true;
          }
        }
 catch (        MessageRemovedException ignore) {
          if (log.isDebugEnabled()) {
            log.debug("Skipping message #: " + messages[i].getMessageNumber() + " as it has been DELETED by another thread after processing");
          }
          latch.countDown();
        }
      }
      if (!mailProcessingStarted) {
        onCompletion.run();
      }
    }
 catch (    MessagingException me) {
      processFailure("Error checking mail for account : " + emailAddress + " :: "+ me.getMessage(),me,entry);
    }
  }
 else {
    processFailure("Unable to access mail folder",null,entry);
  }
}