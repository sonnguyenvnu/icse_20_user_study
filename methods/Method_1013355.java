/** 
 * This method returns true or false for whether two LiveExprNodes are syntactically equal.
 */
public boolean equals(LiveExprNode exp){
  if (exp instanceof LNNeg) {
    return getBody().equals(((LNNeg)exp).getBody());
  }
  return false;
}
