public TcpConnector connect(){
  if (isClosed()) {
    log.infof("Connect socket <-> %s:%d",host,port);
    try {
      socket=new Socket(InetAddress.getByName(host),port);
      socket.setTcpNoDelay(true);
      reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer=new OutputStreamWriter(socket.getOutputStream());
    }
 catch (    UnknownHostException e) {
      log.warnf("Unknown host '%s:%d'",host,port);
      throw Lang.wrapThrow(e);
    }
catch (    IOException e) {
      log.warnf("IOError '%s:%d'",host,port);
      throw Lang.wrapThrow(e);
    }
  }
  return this;
}
