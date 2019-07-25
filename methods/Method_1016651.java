@Override public SelectionCoreImpl max(final Object column){
  return addToCurrentColumn(FunctionFactory.max(column));
}
