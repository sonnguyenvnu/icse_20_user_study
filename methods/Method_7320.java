public static byte[] computeSHA512(byte[] convertme,byte[] convertme2,byte[] convertme3){
  try {
    MessageDigest md=MessageDigest.getInstance("SHA-512");
    md.update(convertme,0,convertme.length);
    md.update(convertme2,0,convertme2.length);
    md.update(convertme3,0,convertme3.length);
    return md.digest();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  return new byte[64];
}
