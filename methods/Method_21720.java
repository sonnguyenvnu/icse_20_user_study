protected void updateRequestWithPreference(Select select,SearchRequestBuilder request){
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.PREFERENCE && hint.getParams() != null && 0 < hint.getParams().length) {
      request.setPreference(hint.getParams()[0].toString());
    }
  }
}
