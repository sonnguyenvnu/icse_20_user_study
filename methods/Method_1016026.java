public static byte[] read(final InputStream source) throws IOException {
  final ByteArrayOutputStream baos=new ByteArrayOutputStream();
  final byte[] buffer=new byte[2048];
  int c;
  while ((c=source.read(buffer,0,2048)) > 0)   baos.write(buffer,0,c);
  baos.flush();
  baos.close();
  return baos.toByteArray();
}
