/** 
 * 16?? ? 10?? <p>???stringHex2StringDecimal("FFFF");</p> <ul>????16???10????????? <li>@see #stringHex2IntDecimal(String) {@link #stringHex2IntDecimal(String)}</li> <li>@see #int2String2(int) {@link #int2String2(int)}</li> </ul>
 * @param data 10?? ???
 * @return 16?? ???
 */
public static String stringHex2StringDecimal(@NotNull String data){
  return Integer.valueOf(data,16).toString();
}
