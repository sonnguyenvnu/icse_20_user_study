static public String inputStreamToString(InputStream is,String encoding) throws IOException {
  Reader reader=new InputStreamReader(is,encoding);
  try {
    return readerToString(reader);
  }
  finally {
    reader.close();
  }
}
