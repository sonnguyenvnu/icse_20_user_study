/** 
 * String?int???2???
 * @param data
 * @param isUnsignedInt
 * @return String???{@link #string2Int(String)}<p> {@link #string2Int(String,boolean)}</p> <p> {@link #string2IntOctal(String,boolean)}</p> <p> {@link #string2IntHexadecimal(String,boolean)}</p> <p> {@link #string2Int2(String)}</p>
 */
public static int string2IntBinary(@NotNull String data,@NotNull boolean isUnsignedInt){
  return string2Int(data,isUnsignedInt,2);
}
