protected void _XXXXX_(TCPEndpoint endpoint) throws AxisFault {
  try {
    TCPServer server=new TCPServer(endpoint,workerPool);
    server.startServer();
    serverTable.put(endpoint,server);
  }
 catch (  IOException e) {
    handleException("Error while starting the TCP endpoint",e);
  }
}