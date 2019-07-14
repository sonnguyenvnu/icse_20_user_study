/** 
 * 8?? ? 10?? <p>???stringOctal2StringDecimal("121");</p>
 * @param data 8?? ???
 * @return 10?? ???
 */
public static String stringOctal2StringDecimal(@NotNull String data){
  return Integer.valueOf(data,8).toString();
}
