public static byte[] computeSHA1(ByteBuffer convertme,int offset,int len){
  int oldp=convertme.position();
  int oldl=convertme.limit();
  try {
    MessageDigest md=MessageDigest.getInstance("SHA-1");
    convertme.position(offset);
    convertme.limit(len);
    md.update(convertme);
    return md.digest();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    convertme.limit(oldl);
    convertme.position(oldp);
  }
  return new byte[20];
}
