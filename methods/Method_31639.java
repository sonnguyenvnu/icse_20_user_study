@Override public Reader read(){
  InputStream inputStream=classLoader.getResourceAsStream(fileNameWithAbsolutePath);
  if (inputStream == null) {
    throw new FlywayException("Unable to obtain inputstream for resource: " + fileNameWithAbsolutePath);
  }
  return new InputStreamReader(inputStream,encoding.newDecoder());
}
