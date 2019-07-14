@Override public Object[] getViolationParameters(DataPoint point){
  LOGGER.entering(CLASS_NAME,"visit(getViolationParameters)");
  if (LOGGER.isLoggable(Level.FINE)) {
    LOGGER.fine("Node Count ==" + point.getScore());
  }
  return new String[]{String.valueOf((int)point.getScore())};
}
