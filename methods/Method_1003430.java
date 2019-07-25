/** 
 * Read a file from a client.
 * @param fileName the target file name
 */
synchronized void receive(String fileName) throws IOException {
  connect();
  try {
    InputStream in=socket.getInputStream();
    OutputStream out=FileUtils.newOutputStream(fileName,false);
    IOUtils.copy(in,out);
    out.close();
  }
  finally {
    socket.close();
  }
  server.trace("closed");
}
