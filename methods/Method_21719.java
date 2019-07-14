protected void updateRequestWithIndexAndRoutingOptions(Select select,SearchRequestBuilder request){
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.IGNORE_UNAVAILABLE) {
      request.setIndicesOptions(IndicesOptions.fromOptions(true,false,true,false,IndicesOptions.strictExpandOpenAndForbidClosed()));
    }
    if (hint.getType() == HintType.ROUTINGS) {
      Object[] routings=hint.getParams();
      String[] routingsAsStringArray=new String[routings.length];
      for (int i=0; i < routings.length; i++) {
        routingsAsStringArray[i]=routings[i].toString();
      }
      request.setRouting(routingsAsStringArray);
    }
  }
}
