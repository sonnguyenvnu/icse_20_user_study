public static String hexdigest(byte[] bytes){
  String s=null;
  try {
    MessageDigest md=MessageDigest.getInstance("MD5");
    md.update(bytes);
    byte[] tmp=md.digest();
    char[] str=new char[32];
    int k=0;
    for (int i=0; i < 16; ++i) {
      byte byte0=tmp[i];
      str[k++]=hexDigits[byte0 >>> 4 & 15];
      str[k++]=hexDigits[byte0 & 15];
    }
    s=new String(str);
  }
 catch (  Exception var8) {
    var8.printStackTrace();
  }
  return s;
}
