@Override @Deprecated public int getArrayDepth(){
  if (!isArray()) {
    return 0;
  }
  return checkType() + checkDecl();
}
