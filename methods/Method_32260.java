public long getMillis(long value){
  long scaled=FieldUtils.safeMultiply(value,iScalar);
  return getWrappedField().getMillis(scaled);
}
