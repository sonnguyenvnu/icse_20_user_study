public static void uncompress(ByteBuffer compressed,ByteBuffer uncompressed) throws IOException {
  SNAPPY.uncompress(compressed,uncompressed);
}
