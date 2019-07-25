private boolean process(AggregateItem<T> value,ResultHolder holder){
  if (value == null) {
    LOG.debug("Exhausted ItemReader");
    holder.setExhausted(true);
    return false;
  }
  if (value.isHeader()) {
    LOG.debug("Start of new record detected");
    return true;
  }
  if (value.isFooter()) {
    LOG.debug("End of record detected");
    return false;
  }
  LOG.debug("Mapping: " + value);
  holder.addRecord(value.getItem());
  return true;
}
