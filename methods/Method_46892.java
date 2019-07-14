private String getMD5Checksum() throws Exception {
  byte[] b=createChecksum();
  String result="";
  for (  byte aB : b) {
    result+=Integer.toString((aB & 0xff) + 0x100,16).substring(1);
  }
  return result;
}
