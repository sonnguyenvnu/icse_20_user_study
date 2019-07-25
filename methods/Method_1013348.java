public boolean equals(LiveExprNode exp){
  if (exp instanceof LNConj) {
    LNConj exp2=(LNConj)exp;
    if (getCount() != exp2.getCount()) {
      return false;
    }
    for (int i=0; i < getCount(); i++) {
      if (!getBody(i).equals(exp2.getBody(i))) {
        return false;
      }
    }
    return true;
  }
  return false;
}
