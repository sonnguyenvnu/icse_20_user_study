private boolean hasTypeThrowable(ASTPrimaryExpression last){
  return last.getType() != null && TypeHelper.isA(last,Throwable.class);
}
