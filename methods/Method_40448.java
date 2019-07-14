@Override public boolean visit(Num n){
  addStyle(n,StyleRun.Type.NUMBER);
  return true;
}
