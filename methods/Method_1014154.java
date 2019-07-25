private void listen(int port){
  try {
    socket=new ServerSocket(port);
    logger.info("Started Language Server Protocol (LSP) service on port {}",port);
    while (!socket.isClosed()) {
      logger.debug("Going to wait for a client to connect");
      try {
        Socket client=socket.accept();
        pool.submit(() -> handleConnection(client));
      }
 catch (      IOException e) {
        if (!socket.isClosed()) {
          logger.error("Error accepting client connection: {}",e.getMessage());
        }
      }
    }
  }
 catch (  IOException e) {
    logger.error("Error starting the Language Server",e);
  }
}
