private boolean checkAll(List<VarSymbol> formals,List<? extends ExpressionTree> actuals,VisitorState state){
  if (formals.size() != actuals.size()) {
    return false;
  }
  boolean hasFinding=false;
  for (int i=0; i < formals.size(); i++) {
    hasFinding|=check(formals.get(i).getSimpleName().toString(),actuals.get(i),state);
  }
  return hasFinding;
}
