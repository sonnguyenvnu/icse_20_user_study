@Override public SearchRequestBuilder count(final Client client,final String type) throws NoIndexSelectedException {
  return client.prepareSearch(readIndices()).setIndicesOptions(options()).setTypes(type).setSource(new SearchSourceBuilder().size(0));
}
