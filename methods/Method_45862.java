public int uncompress(byte[] compressed,int compressedOffset,int compressedSize,byte[] uncompressed,int uncompressedOffset) throws CorruptionException {
  return SnappyDecompressor.uncompress(compressed,compressedOffset,compressedSize,uncompressed,uncompressedOffset);
}
