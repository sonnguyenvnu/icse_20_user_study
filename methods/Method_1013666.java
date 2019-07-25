@Override public void mark(ALERT_TYPE alertType,LongSupplier checkInterval){
  logger.info("[mark]{}, {}ms",alertType,checkInterval.getAsLong());
  checkIntervals.put(alertType,checkInterval);
}
