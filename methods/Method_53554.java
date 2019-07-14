/** 
 * String?int???8???
 * @param data
 * @param isUnsignedInt
 * @return String???<p> {@link #string2Int(String)}</p> <p> {@link #string2Int(String,boolean)}</p> <p> {@link #string2IntBinary(String,boolean)}</p> <p> {@link #string2IntHexadecimal(String,boolean)}</p> <p> {@link #string2Int2(String)}</p>
 */
public static int string2IntOctal(@NotNull String data,@NotNull boolean isUnsignedInt){
  return string2Int(data,isUnsignedInt,8);
}
