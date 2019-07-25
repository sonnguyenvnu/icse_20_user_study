@Override public byte[] request(final byte[] outputBytes,final SocketAddress address) throws IOException {
  InputStream inputStream=null;
  OutputStream outputStream=null;
  ByteArrayOutputStream cache=null;
  Socket socket=null;
  if (proxy != null) {
    socket=new SocksSocket(proxy);
  }
 else {
    socket=new Socket();
  }
  byte[] response=null;
  IOException exception=null;
  try {
    if (socketInitializer != null) {
      socket=socketInitializer.init(socket);
    }
    socket.connect(address);
    outputStream=new BufferedOutputStream(socket.getOutputStream());
    inputStream=new BufferedInputStream(socket.getInputStream());
    outputStream.write(outputBytes);
    outputStream.flush();
    cache=new ByteArrayOutputStream();
    byte[] buffer=new byte[1024 * 5];
    int length=0;
    while ((length=inputStream.read(buffer)) > 0) {
      cache.write(buffer,0,length);
    }
    response=cache.toByteArray();
  }
 catch (  IOException e) {
    exception=e;
  }
 finally {
    ResourceUtil.close(inputStream);
    ResourceUtil.close(outputStream);
    ResourceUtil.close(socket);
  }
  if (exception != null) {
    throw exception;
  }
  return response;
}
