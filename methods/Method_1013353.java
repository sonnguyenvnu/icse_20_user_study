/** 
 * This method returns true or false for whether two LiveExprNodes are syntactically equal.
 */
public boolean equals(LiveExprNode exp){
  if (exp instanceof LNEven) {
    return getBody().equals(((LNEven)exp).getBody());
  }
  return false;
}
