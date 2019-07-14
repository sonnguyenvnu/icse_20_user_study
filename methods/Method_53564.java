/** 
 * 16?? ? 10?? ???stringHex2IntDecimal("FFFF");
 * @param data 10?? ???
 * @return 16?? int??????16???10????????? <p> {@link #stringHex2StringDecimal(String)}</p> <p> {@link #stringHex2IntDecimal(String)}</p> <p> {@link #int2String2(int)}</p>
 */
public static int stringHex2IntDecimal(@NotNull String data){
  return Integer.parseInt(data,16);
}
