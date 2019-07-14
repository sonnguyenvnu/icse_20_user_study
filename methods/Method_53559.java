/** 
 * String? long ??
 * @param data
 * @param isUnsignedLong ????????? long ??
 * @return ??? long ??
 */
public static long string2Long(@NotNull String data,@NotNull boolean isUnsignedLong){
  if (isUnsignedLong == false) {
    return Long.parseLong(data);
  }
 else {
    return Long.parseUnsignedLong(data);
  }
}
