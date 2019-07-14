/** 
 * byte ?? ??? 16???String???
 * @param byteArr byte ??
 * @return 16???String???
 * @deprecated ??????????bug, ???{@link #byteArr2StringHex2(byte[])} ???
 */
@Deprecated public static String byteArr2StringHex(@NotNull byte[] byteArr){
  StringBuilder stringBuilder=new StringBuilder("");
  if (byteArr == null || byteArr.length <= 0) {
    return null;
  }
  for (int i=0; i < byteArr.length; i++) {
    int v=byteArr[i] & 0xFF;
    String hv=Integer.toHexString(v);
    if (hv.length() < 2) {
      stringBuilder.append(0);
    }
    stringBuilder.append(hv);
  }
  return stringBuilder.toString();
}
