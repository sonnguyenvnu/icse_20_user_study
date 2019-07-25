private void stor(String fileName,Socket socket) throws UnknownHostException, IOException {
  final InputStream is=socket.getInputStream();
  final ByteArrayOutputStream baos=new ByteArrayOutputStream();
  FileUtils.copyToStream(is,baos);
  myOut("226 Transfer complete.");
  if ("png".equalsIgnoreCase(fileName)) {
    connexion.setFileFormat(FileFormat.PNG);
  }
 else   if ("svg".equalsIgnoreCase(fileName)) {
    connexion.setFileFormat(FileFormat.SVG);
  }
 else   if ("eps".equalsIgnoreCase(fileName)) {
    connexion.setFileFormat(FileFormat.EPS);
  }
  if (fileName.length() > 3) {
    final String data=new String(baos.toByteArray(),ftpServer.getCharset());
    final String pngFileName=connexion.getFutureFileName(fileName);
    connexion.futureOutgoing(pngFileName);
    connexion.addIncoming(fileName,data);
    ftpServer.processImage(connexion,fileName);
  }
}
