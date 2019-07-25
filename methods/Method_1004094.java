/** 
 * Requests a dump from the given end-point.
 * @param address host name or IP-Address to connect to
 * @param port port to connect to
 * @return container for the dumped data
 * @throws IOException in case the dump can not be requested
 */
public ExecFileLoader dump(final InetAddress address,final int port) throws IOException {
  final ExecFileLoader loader=new ExecFileLoader();
  final Socket socket=tryConnect(address,port);
  try {
    final RemoteControlWriter remoteWriter=new RemoteControlWriter(socket.getOutputStream());
    final RemoteControlReader remoteReader=new RemoteControlReader(socket.getInputStream());
    remoteReader.setSessionInfoVisitor(loader.getSessionInfoStore());
    remoteReader.setExecutionDataVisitor(loader.getExecutionDataStore());
    remoteWriter.visitDumpCommand(dump,reset);
    if (!remoteReader.read()) {
      throw new IOException("Socket closed unexpectedly.");
    }
  }
  finally {
    socket.close();
  }
  return loader;
}
