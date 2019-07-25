@Override public boolean test(final LazyElementCell elementCell){
  return validator.validateInput(elementCell.getElement());
}
