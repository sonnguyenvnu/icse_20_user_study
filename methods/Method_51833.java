@Override @Deprecated public boolean isArray(){
  return checkType() + checkDecl() > 0;
}
