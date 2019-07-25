@Override public Flux<SearchHit> search(HttpHeaders headers,SearchRequest searchRequest){
  return sendRequest(searchRequest,RequestCreator.search(),SearchResponse.class,headers).map(SearchResponse::getHits).flatMap(Flux::fromIterable);
}
