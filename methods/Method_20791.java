public static byte[] readFully(final @NonNull InputStream inputStream) throws IOException {
  final ByteArrayOutputStream out=new ByteArrayOutputStream();
  final byte[] buffer=new byte[1024];
  for (int count; (count=inputStream.read(buffer)) != -1; ) {
    out.write(buffer,0,count);
  }
  return out.toByteArray();
}
