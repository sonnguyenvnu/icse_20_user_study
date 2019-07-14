/** 
 * String?int??
 * @param data String??
 * @return int??<p> {@link #string2Int(String)}</p> <p> {@link #string2Int(String,boolean)}</p> <p> {@link #string2IntBinary(String,boolean)}</p> <p> {@link #string2IntOctal(String,boolean)}</p> <p> {@link #string2IntHexadecimal(String,boolean)}</p>
 */
public static int string2Int2(@NotNull String data){
  return Integer.valueOf(data).intValue();
}
