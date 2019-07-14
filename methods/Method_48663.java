@Override public Float convert(Object value){
  if (value instanceof Number) {
    final double d=((Number)value).doubleValue();
    if (d < -Float.MAX_VALUE || d > Float.MAX_VALUE)     throw new IllegalArgumentException("Value too large for float: " + value);
    return (float)d;
  }
 else   if (value instanceof String) {
    return Float.parseFloat((String)value);
  }
 else   return null;
}
