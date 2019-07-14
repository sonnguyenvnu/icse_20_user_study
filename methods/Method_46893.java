private String getSHA256Checksum() throws NoSuchAlgorithmException, IOException {
  MessageDigest messageDigest=MessageDigest.getInstance("SHA-256");
  byte[] input=new byte[GenericCopyUtil.DEFAULT_BUFFER_SIZE];
  int length;
  InputStream inputStream=file.getInputStream(context);
  while ((length=inputStream.read(input)) != -1) {
    if (length > 0)     messageDigest.update(input,0,length);
  }
  byte[] hash=messageDigest.digest();
  StringBuilder hexString=new StringBuilder();
  for (  byte aHash : hash) {
    String hex=Integer.toHexString(0xff & aHash);
    if (hex.length() == 1)     hexString.append('0');
    hexString.append(hex);
  }
  inputStream.close();
  return hexString.toString();
}
