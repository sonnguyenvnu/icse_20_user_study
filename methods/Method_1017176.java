public SearchRequestBuilder search(String type) throws NoIndexSelectedException {
  return index.search(client.getClient(),type);
}
