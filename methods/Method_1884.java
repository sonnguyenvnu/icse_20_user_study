/** 
 * We manage the Lossless WebP case
 * @param is The InputStream we're reading
 * @return The dimensions if any
 * @throws IOException In case or error reading from the InputStream
 */
private static @Nullable Pair<Integer,Integer> getVP8LDimension(final InputStream is) throws IOException {
  getInt(is);
  final byte check=getByte(is);
  if (check != 0x2F) {
    return null;
  }
  int data1=((byte)is.read()) & 0xFF;
  int data2=((byte)is.read()) & 0xFF;
  int data3=((byte)is.read()) & 0xFF;
  int data4=((byte)is.read()) & 0xFF;
  final int width=((data2 & 0x3F) << 8 | data1) + 1;
  final int height=((data4 & 0x0F) << 10 | data3 << 2 | (data2 & 0xC0) >> 6) + 1;
  return new Pair<>(width,height);
}
