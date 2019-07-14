private void setLimitFromHint(List<Hint> hints){
  int from=0;
  int size=0;
  for (  Hint hint : hints) {
    if (hint.getType() == HintType.DOCS_WITH_AGGREGATION) {
      Integer[] params=(Integer[])hint.getParams();
      if (params.length > 1) {
        from=params[0];
        size=params[1];
      }
 else       if (params.length == 1) {
        size=params[0];
      }
      break;
    }
  }
  request.setFrom(from);
  request.setSize(size);
}
