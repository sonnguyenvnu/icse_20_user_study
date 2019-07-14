@Override protected void updateRequestWithHints(JoinRequestBuilder requestBuilder){
  super.updateRequestWithHints(requestBuilder);
  for (  Hint hint : joinSelect.getHints()) {
    if (hint.getType() == HintType.HASH_WITH_TERMS_FILTER) {
      ((HashJoinElasticRequestBuilder)requestBuilder).setUseTermFiltersOptimization(true);
    }
  }
}
