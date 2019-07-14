@Override public Object[] getViolationParameters(DataPoint point){
  return new String[]{String.valueOf((int)point.getScore())};
}
