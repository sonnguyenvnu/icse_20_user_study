/** 
 * byte???int 10??
 * @param byteArr byte ??
 * @return int??{@link #byteArr2Int2(byte[])} ????????????????
 */
public static int byteArr2Int(@NotNull byte[] byteArr){
  return (byteArr[0] & 0xff) << 24 | (byteArr[1] & 0xff) << 16 | (byteArr[2] & 0xff) << 8 | (byteArr[3] & 0xff);
}
