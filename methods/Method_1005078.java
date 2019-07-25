@Override public boolean test(final LazyElementCell elementCell){
  return validator.validateAggregation(elementCell.getElement());
}
