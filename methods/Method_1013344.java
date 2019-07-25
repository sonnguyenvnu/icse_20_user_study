public boolean equals(LiveExprNode exp){
  if (exp instanceof LNAll) {
    return getBody().equals(((LNAll)exp).getBody());
  }
  return false;
}
