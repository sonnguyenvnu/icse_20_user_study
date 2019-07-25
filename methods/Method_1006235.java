private void initialize(InputStream stream) throws IOException {
  Objects.requireNonNull(stream);
  try (Reader reader=new InputStreamReader(stream,encoding)){
    readFormatFile(reader);
  }
 }
