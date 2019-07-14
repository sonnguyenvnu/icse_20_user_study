public static int getUncompressedLength(byte[] compressed,int compressedOffset) throws CorruptionException {
  return readUncompressedLength(compressed,compressedOffset)[0];
}
