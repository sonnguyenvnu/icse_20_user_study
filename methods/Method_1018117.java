@Override public void index(@Nonnull String indexName,@Nonnull String typeName,@Nonnull String id,@Nonnull Map<String,Object> fields){
  try (RestClient restClient=buildRestClient()){
    JSONObject jsonContent=new JSONObject(fields);
    HttpEntity httpEntity=new NStringEntity(jsonContent.toString(),ContentType.APPLICATION_JSON);
    restClient.performRequest(PUT_METHOD,getIndexWriteEndPoint(indexName,typeName,id),Collections.emptyMap(),httpEntity);
    log.debug("Wrote to index with name {}",indexName);
  }
 catch (  ResponseException responseException) {
    log.warn("Index write encountered issues in Elasticsearch for index={" + indexName + "}, type={" + typeName + "}, id={" + id + "}",responseException);
  }
catch (  ClientProtocolException clientProtocolException) {
    log.debug("Http protocol error for write for index {" + indexName + "}",clientProtocolException);
  }
catch (  IOException ioException) {
    log.error("IO Error in rest client",ioException);
  }
}
