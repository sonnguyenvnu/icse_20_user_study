@Override public SqlElasticSearchRequestBuilder explain() throws SqlParseException {
  Hint scrollHint=null;
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.USE_SCROLL) {
      scrollHint=hint;
      break;
    }
  }
  if (scrollHint != null && scrollHint.getParams()[0] instanceof String) {
    return new SqlElasticSearchRequestBuilder(new SearchScrollRequestBuilder(client,SearchScrollAction.INSTANCE,(String)scrollHint.getParams()[0]).setScroll(new TimeValue((Integer)scrollHint.getParams()[1])));
  }
  this.request=new SearchRequestBuilder(client,SearchAction.INSTANCE);
  setIndicesAndTypes();
  setFields(select.getFields());
  setWhere(select.getWhere());
  setSorts(select.getOrderBys());
  setLimit(select.getOffset(),select.getRowCount());
  if (scrollHint != null) {
    if (!select.isOrderdSelect())     request.addSort(FieldSortBuilder.DOC_FIELD_NAME,SortOrder.ASC);
    request.setSize((Integer)scrollHint.getParams()[0]).setScroll(new TimeValue((Integer)scrollHint.getParams()[1]));
  }
 else {
    request.setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
  }
  updateRequestWithIndexAndRoutingOptions(select,request);
  updateRequestWithHighlight(select,request);
  updateRequestWithCollapse(select,request);
  updateRequestWithPostFilter(select,request);
  updateRequestWithStats(select,request);
  updateRequestWithPreference(select,request);
  SqlElasticSearchRequestBuilder sqlElasticRequestBuilder=new SqlElasticSearchRequestBuilder(request);
  return sqlElasticRequestBuilder;
}
