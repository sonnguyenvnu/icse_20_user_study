private MultiSearchRequest createMultiSearchRequest(int multiSearchMaxSize,Where connectedWhere,SearchHit[] hits,Select secondTableSelect,Where originalWhere,int currentIndex) throws SqlParseException {
  MultiSearchRequest multiSearchRequest=new MultiSearchRequest();
  for (int i=currentIndex; i < currentIndex + multiSearchMaxSize && i < hits.length; i++) {
    Map<String,Object> hitFromFirstTableAsMap=hits[i].getSourceAsMap();
    Where newWhere=Where.newInstance();
    if (originalWhere != null)     newWhere.addWhere(originalWhere);
    if (connectedWhere != null) {
      Where connectedWhereCloned=null;
      try {
        connectedWhereCloned=(Where)connectedWhere.clone();
      }
 catch (      CloneNotSupportedException e) {
        e.printStackTrace();
      }
      updateValuesOnWhereConditions(hitFromFirstTableAsMap,connectedWhereCloned);
      newWhere.addWhere(connectedWhereCloned);
    }
    if (newWhere.getWheres().size() != 0) {
      secondTableSelect.setWhere(newWhere);
    }
    DefaultQueryAction action=new DefaultQueryAction(this.client,secondTableSelect);
    action.explain();
    SearchRequestBuilder secondTableRequest=action.getRequestBuilder();
    Integer secondTableHintLimit=this.nestedLoopsRequest.getSecondTable().getHintLimit();
    if (secondTableHintLimit != null && secondTableHintLimit <= MAX_RESULTS_ON_ONE_FETCH)     secondTableRequest.setSize(secondTableHintLimit);
    multiSearchRequest.add(secondTableRequest);
  }
  return multiSearchRequest;
}
