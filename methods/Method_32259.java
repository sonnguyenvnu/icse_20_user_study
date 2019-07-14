public long getMillis(int value){
  long scaled=((long)value) * ((long)iScalar);
  return getWrappedField().getMillis(scaled);
}
