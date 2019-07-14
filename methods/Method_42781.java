/** 
 * ?????
 * @param phone
 * @return
 */
public static boolean isPostCode(String post){
  Pattern pattern=Pattern.compile("^[0-9]{6}$");
  return pattern.matcher(post).matches();
}
