public void write(OutputStream outputStream) throws IOException {
  InputStream inputStream=null;
  try {
    inputStream=getInputStream();
    IOUtils.copy(inputStream,outputStream);
  }
  finally {
    IOUtils.closeQuietly(inputStream);
  }
}
