@Override protected boolean test(Object o){
  return !ExpressionUtils.isNonBlankData(o);
}
