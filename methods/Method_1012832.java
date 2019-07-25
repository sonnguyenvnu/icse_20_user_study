/** 
 * @param rawPassword ????
 * @param encodedPassword ??????
 * @return
 */
public static boolean matches(CharSequence rawPassword,String encodedPassword){
  return encoder.matches(rawPassword,encodedPassword);
}
