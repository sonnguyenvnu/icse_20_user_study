public boolean equals(LiveExprNode exp){
  if (exp instanceof LNAction) {
    return getTag() == ((LNAction)exp).getTag();
  }
  return false;
}
