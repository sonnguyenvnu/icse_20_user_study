@Override public void run(){
  boolean executing=false;
  boolean reset=false;
  Throwable error=null;
  try {
    logger.info("Gateway Connection ready to receive messages");
    String commandLine=null;
    do {
      commandLine=reader.readLine();
      executing=true;
      logger.fine("Received command: " + commandLine);
      Command command=commands.get(commandLine);
      if (command != null) {
        if (authCommand != null && !authCommand.isAuthenticated()) {
          authCommand.execute(commandLine,reader,writer);
        }
 else {
          command.execute(commandLine,reader,writer);
        }
        executing=false;
      }
 else {
        reset=true;
        throw new Py4JException("Unknown command received: " + commandLine);
      }
    }
 while (commandLine != null && !commandLine.equals("q"));
  }
 catch (  SocketTimeoutException ste) {
    logger.log(Level.WARNING,"Timeout occurred while waiting for a command.",ste);
    error=ste;
    reset=true;
  }
catch (  Py4JAuthenticationException pae) {
    logger.log(Level.SEVERE,"Authentication error.",pae);
    reset=true;
  }
catch (  Exception e) {
    logger.log(Level.WARNING,"Error occurred while waiting for a command.",e);
    error=e;
  }
 finally {
    if (error != null && executing && writer != null) {
      quietSendFatalError(writer,error);
    }
    shutdown(reset);
  }
}
