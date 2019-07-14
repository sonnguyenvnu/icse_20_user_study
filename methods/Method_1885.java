/** 
 * We manage the Extended WebP case
 * @param is The InputStream we're reading
 * @return The dimensions if any
 * @throws IOException In case or error reading from the InputStream
 */
private static Pair<Integer,Integer> getVP8XDimension(final InputStream is) throws IOException {
  is.skip(8);
  return new Pair<>(read3Bytes(is) + 1,read3Bytes(is) + 1);
}
