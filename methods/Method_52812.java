@Override public Object[] getViolationParameters(DataPoint point){
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("Point score is " + point.getScore());
  }
  return new String[]{String.valueOf((int)point.getScore())};
}
