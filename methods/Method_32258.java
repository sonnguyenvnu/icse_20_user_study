public long getValueAsLong(long duration,long instant){
  return getWrappedField().getValueAsLong(duration,instant) / iScalar;
}
