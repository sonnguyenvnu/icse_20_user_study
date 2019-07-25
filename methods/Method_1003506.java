@Override public <T>T query(SearchQuery query,ResultsExtractor<T> resultsExtractor){
  SearchResponse response=doSearch(prepareSearch(query),query);
  return resultsExtractor.extract(response);
}
