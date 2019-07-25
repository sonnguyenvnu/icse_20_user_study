private void consume(SearchResponse response,Consumer<? super Response> onResponse){
  onResponse.accept(wrap(response));
}
