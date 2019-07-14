public static byte[] computeSHA256(byte[] convertme,int offset,int len){
  try {
    MessageDigest md=MessageDigest.getInstance("SHA-256");
    md.update(convertme,offset,len);
    return md.digest();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return new byte[32];
}
