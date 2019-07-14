@Override public void createIndex(String indexName,Map<String,Object> settings) throws IOException {
  performRequest(REQUEST_TYPE_PUT,REQUEST_SEPARATOR + indexName,mapWriter.writeValueAsBytes(settings));
}
