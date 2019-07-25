public boolean equals(LiveExprNode exp){
  if (exp instanceof LNState) {
    return getTag() == ((LNState)exp).getTag();
  }
  return false;
}
