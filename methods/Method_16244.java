/** 
 * ??????????
 * @param secretBase32 ??
 * @param code         ????????
 * @return
 */
public static boolean verify(String secretBase32,String code){
  return generate(secretBase32).equals(code);
}
