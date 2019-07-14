protected void updateRequestWithPostFilter(Select select,SearchRequestBuilder request){
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.POST_FILTER && hint.getParams() != null && 0 < hint.getParams().length) {
      request.setPostFilter(QueryBuilders.wrapperQuery(hint.getParams()[0].toString()));
    }
  }
}
