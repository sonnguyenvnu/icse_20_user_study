/** 
 * byte ?? ???String??? 10??
 * @param byteArr byte ??
 * @return String???
 */
public static String byteArr2String(@NotNull byte[] byteArr){
  try {
    return new String(byteArr,StandardCharsets.UTF_8);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  return null;
}
