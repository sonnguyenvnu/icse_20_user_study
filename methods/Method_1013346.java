public boolean equals(LiveExprNode exp){
  if (exp instanceof LNBool) {
    return b == ((LNBool)exp).b;
  }
  return false;
}
