private static byte[] getSHA1(Path libFile) throws NoSuchAlgorithmException, IOException {
  MessageDigest digest=MessageDigest.getInstance("SHA-1");
  try (InputStream input=Files.newInputStream(libFile)){
    byte[] buffer=new byte[8 * 1024];
    for (int n; (n=input.read(buffer)) != -1; ) {
      digest.update(buffer,0,n);
    }
  }
   return digest.digest();
}
