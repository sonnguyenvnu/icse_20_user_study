@Override public Object _XXXXX_(Object[] arg0){
  value.remove(arg0);
  LOG.info("processRemove: current values are : " + value);
  return ImmutableList.copyOf(value);
}