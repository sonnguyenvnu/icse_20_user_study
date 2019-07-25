@Override public Selection cop(final String column,final String op,final Object arg2){
  return addToCurrentColumn(new SimpleClause(column,op,arg2));
}
