@Override public QueryResult convert(final ResponseBody value) throws IOException {
  try (InputStream is=value.byteStream()){
    MessagePackTraverser traverser=new MessagePackTraverser();
    return traverser.parse(is);
  }
 }
