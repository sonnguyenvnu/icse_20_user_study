/** 
 * ???????????????
 * @param data     byte[]
 * @param toDigits ???????char[]
 * @return ????String
 */
private static String byteArr2StringHex2(@NotNull byte[] data,@NotNull char[] toDigits){
  return charArr2StringHex(byteArr2CharArrHex(data,toDigits));
}
