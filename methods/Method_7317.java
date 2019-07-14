public static byte[] computeSHA512(byte[] convertme){
  try {
    MessageDigest md=MessageDigest.getInstance("SHA-512");
    md.update(convertme,0,convertme.length);
    return md.digest();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return new byte[64];
}
