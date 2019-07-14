public int getValue(long duration,long instant){
  return getWrappedField().getValue(duration,instant) / iScalar;
}
