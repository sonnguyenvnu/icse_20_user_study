protected void updateRequestWithHints(JoinRequestBuilder requestBuilder){
  for (  Hint hint : joinSelect.getHints()) {
    if (hint.getType() == HintType.JOIN_LIMIT) {
      Object[] params=hint.getParams();
      requestBuilder.getFirstTable().setHintLimit((Integer)params[0]);
      requestBuilder.getSecondTable().setHintLimit((Integer)params[1]);
    }
  }
}
