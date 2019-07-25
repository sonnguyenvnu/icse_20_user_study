/** 
 * Wrap an  {@link InputStream} in a {@link SizeLimitInputStream} that sets alimit to the maximum number of bytes to be read from the original input stream. The number of bytes is determined by the high and low value (bytes = high - low). If the high value is less than the low value, the input stream is not wrapped and returned as is.
 * @param input The input stream to wrap.
 * @param high The high value.
 * @param low The low value.
 * @return The resulting input stream.
 */
public static InputStream wrap(InputStream input,long high,long low){
  if (input != null && high > low) {
    long bytes=(high - (low < 0 ? 0 : low)) + 1;
    LOGGER.trace("Using size-limiting stream (" + bytes + " bytes)");
    return new SizeLimitInputStream(input,bytes);
  }
  return input;
}
