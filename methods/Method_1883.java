/** 
 * We manage the Simple WebP case
 * @param is The InputStream we're reading
 * @return The dimensions if any
 * @throws IOException In case or error reading from the InputStream
 */
private static @Nullable Pair<Integer,Integer> getVP8Dimension(final InputStream is) throws IOException {
  is.skip(7);
  final short sign1=getShort(is);
  final short sign2=getShort(is);
  final short sign3=getShort(is);
  if (sign1 != 0x9D || sign2 != 0x01 || sign3 != 0x2A) {
    return null;
  }
  return new Pair<>(get2BytesAsInt(is),get2BytesAsInt(is));
}
