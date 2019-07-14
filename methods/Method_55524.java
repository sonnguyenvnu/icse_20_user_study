private static byte[] getSHA1(URL hashURL) throws IOException {
  byte[] hash=new byte[20];
  try (InputStream sha1=hashURL.openStream()){
    for (int i=0; i < 20; i++) {
      hash[i]=(byte)((Character.digit(sha1.read(),16) << 4) | Character.digit(sha1.read(),16));
    }
  }
   return hash;
}
