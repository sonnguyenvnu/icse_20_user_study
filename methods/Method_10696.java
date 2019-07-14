/** 
 * ?????
 * @param s ?????
 * @return ????????
 */
public static String upperFirstLetter(String s){
  if (isNullString(s) || !Character.isLowerCase(s.charAt(0))) {
    return s;
  }
  return String.valueOf((char)(s.charAt(0) - 32)) + s.substring(1);
}
