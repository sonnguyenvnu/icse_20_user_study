/** 
 * ????????????32???
 */
private static String hexdigest(byte[] paramArrayOfByte){
  final char[] hexDigits={48,49,50,51,52,53,54,55,56,57,97,98,99,100,101,102};
  try {
    MessageDigest localMessageDigest=MessageDigest.getInstance("MD5");
    localMessageDigest.update(paramArrayOfByte);
    byte[] arrayOfByte=localMessageDigest.digest();
    char[] arrayOfChar=new char[32];
    for (int i=0, j=0; ; i++, j++) {
      if (i >= 16) {
        return new String(arrayOfChar);
      }
      int k=arrayOfByte[i];
      arrayOfChar[j]=hexDigits[(0xF & k >>> 4)];
      arrayOfChar[++j]=hexDigits[(k & 0xF)];
    }
  }
 catch (  Exception e) {
  }
  return "";
}
