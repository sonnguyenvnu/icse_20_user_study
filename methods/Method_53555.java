/** 
 * String?int???16???
 * @param data
 * @param isUnsignedInt
 * @return int??<p> {@link #string2Int(String)}</p> <p> {@link #string2Int(String,boolean)}</p> <p> {@link #string2IntBinary(String,boolean)}</p> <p> {@link #string2IntOctal(String,boolean)}</p> <p> {@link #string2Int2(String)}</p>
 */
public static int string2IntHexadecimal(@NotNull String data,@NotNull boolean isUnsignedInt){
  return string2Int(data,isUnsignedInt,16);
}
