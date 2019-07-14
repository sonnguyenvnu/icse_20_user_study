/** 
 * String?int??
 * @param data
 * @param isUnsignedInt
 * @param radix         ?? ??2?Binary??8?Octal??10?Decimal??16?Hexadecimal? ????10??
 * @return int<p> {@link #string2Int(String)}</p> <p> {@link #string2Int(String,boolean)}</p> <p> {@link #string2IntBinary(String,boolean)}</p> <p> {@link #string2IntOctal(String,boolean)}</p> <p> {@link #string2IntHexadecimal(String,boolean)}</p> <p> {@link #string2Int2(String)}</p>
 */
private static int string2Int(@NotNull String data,@NotNull boolean isUnsignedInt,@NotNull int radix){
  if (isUnsignedInt == false) {
    return Integer.parseInt(data,radix);
  }
 else {
    return Integer.parseUnsignedInt(data,radix);
  }
}
