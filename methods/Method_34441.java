@Override public boolean matches(String s,Metric metric){
  if (!isFilterEnabled()) {
    return true;
  }
  boolean matchesFilter=archaiusPropertyFactory.getBooleanProperty(s,false).get();
  LOGGER.debug("Does metric [{}] match filter? [{}]",s,matchesFilter);
  return matchesFilter;
}
