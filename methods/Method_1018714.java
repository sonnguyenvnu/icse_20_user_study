public void start() throws IOException {
  logger.info("Starting Communication Channel on " + address + " at " + port);
  socket=socketFactory.createSocket(address,port);
  socket.setSoTimeout(blockingReadTimeout);
  reader=new BufferedReader(new InputStreamReader(socket.getInputStream(),Charset.forName("UTF-8")));
  writer=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),Charset.forName("UTF-8")));
  if (authToken != null) {
    try {
      NetworkUtil.authToServer(reader,writer,authToken);
    }
 catch (    IOException ioe) {
      shutdown(true);
      throw ioe;
    }
  }
}
