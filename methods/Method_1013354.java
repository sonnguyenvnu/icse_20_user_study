public LiveExprNode simplify(){
  LiveExprNode body1=getBody().simplify();
  if (body1 instanceof LNBool) {
    return new LNBool(!((LNBool)body1).b);
  }
  return new LNNeg(body1);
}
