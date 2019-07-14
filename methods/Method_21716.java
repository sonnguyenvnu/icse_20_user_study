protected void updateRequestWithStats(Select select,SearchRequestBuilder request){
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.STATS && hint.getParams() != null && 0 < hint.getParams().length) {
      request.setStats(Arrays.stream(hint.getParams()).map(Object::toString).toArray(String[]::new));
    }
  }
}
