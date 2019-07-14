/** 
 * byte????int
 * @param byteArr byte ??
 * @return int??{@link #byteArr2Int(byte[])} ????????????????
 */
public static int byteArr2Int2(@NotNull byte[] byteArr){
  int num=byteArr[3] & 0xFF;
  num|=((byteArr[2] << 8) & 0xFF00);
  num|=((byteArr[1] << 16) & 0xFF00);
  num|=((byteArr[0] << 24) & 0xFF00);
  return num;
}
