/** 
 * Send a file to the client. This method waits until the client has connected.
 * @param fileName the source file name
 * @param skip the number of bytes to skip
 */
synchronized void send(String fileName,long skip) throws IOException {
  connect();
  try {
    OutputStream out=socket.getOutputStream();
    InputStream in=FileUtils.newInputStream(fileName);
    IOUtils.skipFully(in,skip);
    IOUtils.copy(in,out);
    in.close();
  }
  finally {
    socket.close();
  }
  server.trace("closed");
}
