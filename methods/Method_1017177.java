public SearchRequestBuilder count(String type) throws NoIndexSelectedException {
  return index.count(client.getClient(),type);
}
