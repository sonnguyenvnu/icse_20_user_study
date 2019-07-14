@Override public Byte convert(Object value){
  if (value instanceof Number) {
    double d=((Number)value).doubleValue();
    if (Double.isNaN(d) || Math.round(d) != d)     throw new IllegalArgumentException("Not a valid byte: " + value);
    long l=((Number)value).longValue();
    if (l >= Byte.MIN_VALUE && l <= Byte.MAX_VALUE)     return (byte)l;
 else     throw new IllegalArgumentException("Value too large for byte: " + value);
  }
 else   if (value instanceof String) {
    return Byte.parseByte((String)value);
  }
 else   return null;
}
