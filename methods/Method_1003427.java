private void process(String command) throws IOException {
  int idx=command.indexOf(' ');
  String param="";
  if (idx >= 0) {
    param=command.substring(idx).trim();
    command=command.substring(0,idx);
  }
  command=StringUtils.toUpperEnglish(command);
  if (command.length() == 0) {
    reply(506,"No command");
    return;
  }
  server.trace(">" + command);
  FtpEventListener listener=server.getEventListener();
  FtpEvent event=null;
  if (listener != null) {
    event=new FtpEvent(this,command,param);
    listener.beforeCommand(event);
  }
  replied=false;
  if (connected) {
    processConnected(command,param);
  }
  if (!replied) {
    if ("USER".equals(command)) {
      userName=param;
      reply(331,"Need password");
    }
 else     if ("QUIT".equals(command)) {
      reply(221,"Bye");
      stop=true;
    }
 else     if ("PASS".equals(command)) {
      if (userName == null) {
        reply(332,"Need username");
      }
 else       if (server.checkUserPasswordWrite(userName,param)) {
        reply(230,"Ok");
        readonly=false;
        connected=true;
      }
 else       if (server.checkUserPasswordReadOnly(userName)) {
        reply(230,"Ok, readonly");
        readonly=true;
        connected=true;
      }
 else {
        reply(431,"Wrong user/password");
      }
    }
 else     if ("REIN".equals(command)) {
      userName=null;
      connected=false;
      currentDir="/";
      reply(200,"Ok");
    }
 else     if ("HELP".equals(command)) {
      reply(214,SERVER_NAME);
    }
  }
  if (!replied) {
    if (listener != null) {
      listener.onUnsupportedCommand(event);
    }
    reply(506,"Invalid command");
  }
  if (listener != null) {
    listener.afterCommand(event);
  }
}
