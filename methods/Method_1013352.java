public LiveExprNode simplify(){
  LiveExprNode body1=getBody().simplify();
  if (body1 instanceof LNEven) {
    body1=((LNEven)body1).getBody();
  }
  return new LNEven(body1);
}
