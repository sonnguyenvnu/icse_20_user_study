@Override public void createMapping(String indexName,String typeName,Map<String,Object> mapping) throws IOException {
  performRequest(REQUEST_TYPE_PUT,REQUEST_SEPARATOR + indexName + REQUEST_SEPARATOR + "_mapping" + REQUEST_SEPARATOR + typeName,mapWriter.writeValueAsBytes(mapping));
}
