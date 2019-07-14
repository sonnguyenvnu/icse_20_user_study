/** 
 * ????????
 * @param s
 * @return
 */
public static boolean isNumer(String s){
  if (isNotEmpty(s,true) == false) {
    return false;
  }
  currentString=s;
  return PATTERN_NUMBER.matcher(s).matches();
}
