private static String encode(String str,String method){
  MessageDigest mdInst=null;
  StringBuilder dstr=new StringBuilder();
  try {
    mdInst=MessageDigest.getInstance(method);
    mdInst.update(str.getBytes());
    byte[] md=mdInst.digest();
    for (int i=0; i < md.length; i++) {
      int tmp=md[i];
      if (tmp < 0) {
        tmp+=256;
      }
      if (tmp < 16) {
        dstr.append("0");
      }
      dstr.append(Integer.toHexString(tmp));
    }
  }
 catch (  NoSuchAlgorithmException e) {
    LOG.error(e);
  }
  return dstr.toString();
}
