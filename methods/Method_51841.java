@Override @Deprecated public int getArrayDepth(){
  if (!isArray()) {
    return 0;
  }
  return getTypeNode().getArrayDepth() + getVariableDeclaratorId().getArrayDepth() + (isVarargs() ? 1 : 0);
}
