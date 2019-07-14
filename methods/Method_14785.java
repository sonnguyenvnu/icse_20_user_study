/** 
 * ???????????????
 * @param s
 * @return
 */
public static boolean isSmallName(String s){
  s=getString(s);
  if (s.isEmpty() || PATTERN_ALPHA_SMALL.matcher(s.substring(0,1)).matches() == false) {
    return false;
  }
  return s.length() <= 1 ? true : isName(s.substring(1));
}
