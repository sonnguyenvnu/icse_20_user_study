/** 
 * ??????????
 * @param s
 * @return
 */
public static boolean isNumberPassword(String s){
  return getLength(s,false) == 6 && isNumer(s);
}
