/** 
 * ?????????????
 * @param email
 * @return ????
 */
public static boolean emailFormat(String email){
  String check="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
  boolean result=Pattern.matches(check,email);
  return result;
}
