/** 
 * ???MD5Hash
 * @param fis
 * @return
 */
public static String md5HashCode(InputStream fis){
  try {
    MessageDigest MD5=MessageDigest.getInstance("MD5");
    byte[] buffer=new byte[8192];
    int length;
    while ((length=fis.read(buffer)) != -1) {
      MD5.update(buffer,0,length);
    }
    return new String(Hex.encodeHex(MD5.digest()));
  }
 catch (  Exception e) {
    logger.error(e.getMessage());
    return null;
  }
}
