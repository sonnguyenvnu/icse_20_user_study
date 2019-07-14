@Override public boolean isIndex(String indexName){
  boolean exists=false;
  try {
    final Response response=delegate.performRequest(REQUEST_TYPE_GET,REQUEST_SEPARATOR + indexName);
    try (final InputStream inputStream=response.getEntity().getContent()){
      exists=mapper.readValue(inputStream,Map.class).containsKey(indexName);
    }
   }
 catch (  final IOException ignored) {
  }
  return exists;
}
