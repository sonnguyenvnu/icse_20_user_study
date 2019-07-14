/** 
 * ?????
 * @param s ?????
 * @return ????????
 */
public static String lowerFirstLetter(String s){
  if (isNullString(s) || !Character.isUpperCase(s.charAt(0))) {
    return s;
  }
  return String.valueOf((char)(s.charAt(0) + 32)) + s.substring(1);
}
