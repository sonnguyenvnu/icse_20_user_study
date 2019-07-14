/** 
 * ?????????
 * @param s
 * @return
 */
public static boolean isVerify(String s){
  return getLength(s,false) >= 4 && isNumer(s);
}
