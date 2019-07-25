@Override public void refresh(String indexName){
  Assert.notNull(indexName,"No index defined for refresh()");
  try {
    client.indices().refresh(refreshRequest(indexName),RequestOptions.DEFAULT);
  }
 catch (  IOException e) {
    throw new ElasticsearchException("failed to refresh index: " + indexName,e);
  }
}
