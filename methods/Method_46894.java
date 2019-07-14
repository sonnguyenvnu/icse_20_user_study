private byte[] createChecksum() throws Exception {
  InputStream fis=file.getInputStream(context);
  byte[] buffer=new byte[8192];
  MessageDigest complete=MessageDigest.getInstance("MD5");
  int numRead;
  do {
    numRead=fis.read(buffer);
    if (numRead > 0) {
      complete.update(buffer,0,numRead);
    }
  }
 while (numRead != -1);
  fis.close();
  return complete.digest();
}
