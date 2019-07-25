public boolean equals(LiveExprNode exp){
  if (exp instanceof LNNext) {
    return getBody().equals(((LNNext)exp).getBody());
  }
  return false;
}
