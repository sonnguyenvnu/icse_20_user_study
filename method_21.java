/** 
 * Return an  {@link ByteBuf} that reads from the provided {@link ByteBuf}, decompresses the data and returns a new InputStream wrapping the underlying payload. Note that src is modified by this call.
 * @return New Input stream with the underlying payload.
 * @throws Exception
 */
public static ByteBuf _XXXXX_(ByteBuf src,StatsLogger statsLogger) throws IOException {
  byte version=src.readByte();
  if (version != CURRENT_VERSION) {
    throw new IOException(String.format("Version mismatch while reading. Received: %d," + " Required: %d",version,CURRENT_VERSION));
  }
  int flags=src.readInt();
  int codecCode=flags & COMPRESSION_CODEC_MASK;
  int originDataLen=src.readInt();
  int actualDataLen=src.readInt();
  ByteBuf compressedBuf=src.slice(src.readerIndex(),actualDataLen);
  ByteBuf decompressedBuf;
  try {
    if (Type.NONE.code() == codecCode && originDataLen != actualDataLen) {
      throw new IOException("Inconsistent data length found for a non-compressed entry : compressed = " + originDataLen + ", actual = "+ actualDataLen);
    }
    CompressionCodec codec=CompressionUtils.getCompressionCodec(Type.of(codecCode));
    decompressedBuf=codec.decompress(compressedBuf,originDataLen);
  }
  finally {
    compressedBuf.release();
  }
  return decompressedBuf;
}