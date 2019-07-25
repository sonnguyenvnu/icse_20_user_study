public LiveExprNode simplify(){
  LiveExprNode body1=getBody().simplify();
  if (body1 instanceof LNAll) {
    body1=((LNAll)body1).getBody();
  }
  return new LNAll(body1);
}
