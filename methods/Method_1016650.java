@Override public SelectionCoreImpl count(final Object column){
  return addToCurrentColumn(FunctionFactory.count(column));
}
