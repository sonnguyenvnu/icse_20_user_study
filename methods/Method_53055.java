static Long toLong(Object value){
  if (value instanceof Long) {
    return (Long)value;
  }
 else   if (value instanceof Number) {
    return ((Number)value).longValue();
  }
 else   if (value instanceof String) {
    try {
      return Long.parseLong((String)value);
    }
 catch (    NumberFormatException ignored) {
    }
  }
  return null;
}
