@Override public SelectionCoreImpl mean(final Object column){
  return addToCurrentColumn(FunctionFactory.mean(column));
}
