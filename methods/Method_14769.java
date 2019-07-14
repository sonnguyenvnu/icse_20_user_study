/** 
 * @param s
 * @param isNull
 * @return s + {@link #isNull(boolean)}
 */
public static String isNull(String s,boolean isNull){
  return s + isNull(isNull);
}
