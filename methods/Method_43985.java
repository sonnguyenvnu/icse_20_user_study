public static <T>T readValue(URL src,Class<T> valueType) throws IOException {
  try (InputStream inputStream=src.openStream()){
    Reader reader=new InputStreamReader(inputStream,Charset.forName("UTF-8"));
    return objectMapperWithoutIndentation.readValue(reader,valueType);
  }
 }
