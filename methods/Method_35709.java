public static boolean isGzipped(byte[] content){
  return content.length >= 2 && content[0] == (byte)GZIPInputStream.GZIP_MAGIC && content[1] == (byte)(GZIPInputStream.GZIP_MAGIC >> 8);
}
