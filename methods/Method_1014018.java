private byte[] hash(Path path){
  try {
    MessageDigest digester=MessageDigest.getInstance("SHA-256");
    if (!Files.exists(path)) {
      return null;
    }
    try (InputStream is=Files.newInputStream(path)){
      byte[] buffer=new byte[4069];
      int read;
      do {
        read=is.read(buffer);
        if (read > 0) {
          digester.update(buffer,0,read);
        }
      }
 while (read != -1);
    }
     return digester.digest();
  }
 catch (  NoSuchAlgorithmException|IOException e) {
    logger.debug("Error calculating the hash of file {}",path,e);
    return null;
  }
}
