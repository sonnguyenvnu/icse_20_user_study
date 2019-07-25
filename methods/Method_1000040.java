public static byte[] decompress(byte[] data) throws IOException {
  ByteArrayOutputStream baos=new ByteArrayOutputStream(data.length);
  ByteArrayInputStream in=new ByteArrayInputStream(data);
  InflaterOutputStream out=new InflaterOutputStream(baos,new Inflater(),BUF_SIZE);
  write(in,out,BUF_SIZE);
  return baos.toByteArray();
}
