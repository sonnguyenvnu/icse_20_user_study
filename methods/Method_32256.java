public long getValueAsLong(long duration){
  return getWrappedField().getValueAsLong(duration) / iScalar;
}
