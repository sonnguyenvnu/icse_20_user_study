/** 
 * Gets color code of the specified point.
 * @param point the specified point
 * @return color code
 */
public static String toCCString(final int point){
  final String hex=Integer.toHexString(point);
  if (1 == hex.length()) {
    return hex + hex + hex + hex + hex + hex;
  }
  if (2 == hex.length()) {
    final String a1=hex.substring(0,1);
    final String a2=hex.substring(1);
    return a1 + a1 + a1 + a2 + a2 + a2;
  }
  if (3 == hex.length()) {
    final String a1=hex.substring(0,1);
    final String a2=hex.substring(1,2);
    final String a3=hex.substring(2);
    return a1 + a1 + a2 + a2 + a3 + a3;
  }
  if (4 == hex.length()) {
    final String a1=hex.substring(0,1);
    final String a2=hex.substring(1,2);
    final String a3=hex.substring(2,3);
    final String a4=hex.substring(3);
    return a1 + a2 + a3 + a4 + a3 + a4;
  }
  if (5 == hex.length()) {
    final String a1=hex.substring(0,1);
    final String a2=hex.substring(1,2);
    final String a3=hex.substring(2,3);
    final String a4=hex.substring(3,4);
    final String a5=hex.substring(4);
    return a1 + a2 + a3 + a4 + a5 + a5;
  }
  if (6 == hex.length()) {
    return hex;
  }
  return hex.substring(0,6);
}
