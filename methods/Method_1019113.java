@Nullable private static byte[] local(@NonNull String url) throws IOException {
  String path=Scheme.FILE.crop(url);
  InputStream inputStream=null;
  inputStream=new FileInputStream(path);
  byte[] bytes=getBytes(inputStream);
  closeStream(inputStream);
  return bytes;
}
