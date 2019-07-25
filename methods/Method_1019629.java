public static void encode(byte[] id,int o,int l,Writer writer) throws Base64Exception {
  try {
    writer.write(encodeChunk(id,o,l));
  }
 catch (  IOException e) {
    throw new Base64Exception("encode() to Writer raised exception",e);
  }
}
