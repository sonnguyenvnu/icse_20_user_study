/** 
 * This method returns true or false for whether two LiveExprNodes are syntactically equal.
 */
public boolean equals(LiveExprNode exp){
  if (exp instanceof LNDisj) {
    LNDisj exp2=(LNDisj)exp;
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
