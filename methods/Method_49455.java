@Override public void deleteIndex(String indexName) throws IOException {
  if (isAlias(indexName)) {
    final String path=new StringBuilder(REQUEST_SEPARATOR).append("_alias").append(REQUEST_SEPARATOR).append(indexName).toString();
    final Response response=performRequest(REQUEST_TYPE_GET,path,null);
    try (final InputStream inputStream=response.getEntity().getContent()){
      final Map<String,Object> records=mapper.readValue(inputStream,new TypeReference<Map<String,Object>>(){
      }
);
      if (records == null)       return;
      for (      final String index : records.keySet()) {
        if (indexExists(index)) {
          performRequest(REQUEST_TYPE_DELETE,REQUEST_SEPARATOR + index,null);
        }
      }
    }
   }
 else   if (indexExists(indexName)) {
    performRequest(REQUEST_TYPE_DELETE,REQUEST_SEPARATOR + indexName,null);
  }
}
