/** 
 * ?????.
 * @param str ??????
 * @return true or false .
 */
public static boolean isLettersOnly(String str){
  Pattern pattern=Pattern.compile("^[A-Za-z]+$");
  return pattern.matcher(str).matches();
}
