private static byte[] getImageFromUrl(String urlText) throws IOException {
  URL url=new URL(urlText);
  ByteArrayOutputStream output=new ByteArrayOutputStream();
  try (InputStream inputStream=url.openStream()){
    int n=0;
    byte[] buffer=new byte[1024];
    while (-1 != (n=inputStream.read(buffer))) {
      output.write(buffer,0,n);
    }
  }
   return output.toByteArray();
}
