/** 
 * Converts a java.lang.String in to a SHA hashed String
 * @param source the source String
 * @return the MD5 hashed version of the string
 */
public static String sha(String source){
  try {
    MessageDigest md=MessageDigest.getInstance("SHA");
    byte[] bytes=md.digest(source.getBytes());
    return getString(bytes);
  }
 catch (  Exception e) {
    e.printStackTrace();
    return null;
  }
}
