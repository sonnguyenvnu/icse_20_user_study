public void run(){
  if (this.context.getBoolean("stop")) {
    if (log.isInfoEnabled())     log.info("stop=true, so, exit ....");
    Sockets.safeClose(socket);
    return;
  }
  if (log.isDebugEnabled())   log.debugf("connect with '%s'",socket.getRemoteSocketAddress().toString());
  try {
    br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
    ops=socket.getOutputStream();
  }
 catch (  IOException e1) {
    return;
  }
  try {
    doRun();
  }
 catch (  SocketException e) {
  }
catch (  CloseSocketException e) {
    if (log.isInfoEnabled())     log.info("Catch CloseSocketException , set lock stop");
    context.set("stop",true);
  }
catch (  IOException e) {
    log.error("Error!! ",e);
  }
 finally {
    if (log.isDebugEnabled())     log.debug("Close socket");
    Sockets.safeClose(socket);
  }
}
