@Override public Object[] getViolationParameters(DataPoint point){
  return new String[]{((ExecutableCode)point.getNode()).getMethodName(),String.valueOf((int)point.getScore())};
}
