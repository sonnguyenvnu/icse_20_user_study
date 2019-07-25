@Override public ContinueStatement clone(){
  return new ContinueStatement(getSourcePosition(),label);
}
