public TimeZone convert(final Object value){
  if (value == null) {
    return null;
  }
  if (value.getClass() == TimeZone.class) {
    return (TimeZone)value;
  }
  return TimeZone.getTimeZone(value.toString());
}
