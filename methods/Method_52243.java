@Override public Object[] getViolationParameters(DataPoint point){
  return new String[]{((ASTMethodDeclaration)point.getNode()).getMethodName(),String.valueOf((int)point.getScore())};
}
