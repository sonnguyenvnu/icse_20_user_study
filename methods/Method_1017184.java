@Override public SearchRequestBuilder count(final Client client,final String type){
  return client.prepareSearch(index).setTypes(type).setSource(new SearchSourceBuilder().size(0));
}
