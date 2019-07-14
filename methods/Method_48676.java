@Override public Long convert(Object value){
  if (value instanceof Number) {
    double d=((Number)value).doubleValue();
    if (Double.isNaN(d) || Math.round(d) != d)     throw new IllegalArgumentException("Not a valid long: " + value);
    return ((Number)value).longValue();
  }
 else   if (value instanceof String) {
    return Long.parseLong((String)value);
  }
 else   if (value instanceof Idfiable) {
    return ((Idfiable)value).longId();
  }
 else   return null;
}
