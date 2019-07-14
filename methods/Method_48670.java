@Override public Integer convert(Object value){
  if (value instanceof Number) {
    final double d=((Number)value).doubleValue();
    Preconditions.checkArgument(!Double.isNaN(d) && Math.round(d) == d,"Not a valid integer: " + value);
    final long l=((Number)value).longValue();
    Preconditions.checkArgument(l >= Integer.MIN_VALUE && l <= Integer.MAX_VALUE,"Value too large for integer: " + value);
    return (int)l;
  }
 else   if (value instanceof String) {
    return Integer.parseInt((String)value);
  }
 else   return null;
}
