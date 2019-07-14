public static byte[] computeSHA256(byte[] b1,int o1,int l1,ByteBuffer b2,int o2,int l2){
  int oldp=b2.position();
  int oldl=b2.limit();
  try {
    MessageDigest md=MessageDigest.getInstance("SHA-256");
    md.update(b1,o1,l1);
    b2.position(o2);
    b2.limit(l2);
    md.update(b2);
    return md.digest();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    b2.limit(oldl);
    b2.position(oldp);
  }
  return new byte[32];
}
