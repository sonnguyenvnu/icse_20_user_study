public long getMillis(long value,long instant){
  long scaled=FieldUtils.safeMultiply(value,iScalar);
  return getWrappedField().getMillis(scaled,instant);
}
