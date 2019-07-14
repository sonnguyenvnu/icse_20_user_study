@Override public Object[] getViolationParameters(DataPoint point){
  return new String[]{((ASTMethod)point.getNode()).getImage(),String.valueOf((int)point.getScore())};
}
