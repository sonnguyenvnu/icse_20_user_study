private static byte[] createChecksum(String filename){
  MessageDigest complete=null;
  try {
    complete=MessageDigest.getInstance("MD5");
  }
 catch (  Exception e) {
  }
  String content=null;
  try {
    final File file=new File(filename);
    content=FileUtils.file2String(file);
  }
 catch (  IOException e) {
  }
  if (content == null) {
    content="";
  }
  byte[] digest=new byte[0];
  if (complete != null) {
    digest=complete.digest(content.getBytes());
  }
  return digest;
}
