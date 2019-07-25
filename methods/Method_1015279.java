/** 
 * ????????????????????????true
 * @param regex        regex
 * @param beTestString beTestString
 */
public static boolean find(String regex,String beTestString){
  Pattern pattern=Pattern.compile(regex);
  Matcher matcher=pattern.matcher(beTestString);
  return matcher.find();
}
