public static byte[] deserialise(final byte[] allBytes,final int lengthSize,final int valueSize,final int delimiter) throws SerialisationException {
  if (null == allBytes || 0 == allBytes.length) {
    return new byte[0];
  }
  return Arrays.copyOfRange(allBytes,delimiter + lengthSize,delimiter + lengthSize + valueSize);
}
