protected Class determineClass(Object value){
  if (value instanceof String) {
    return String.class;
  }
 else   if (value instanceof Character) {
    return Character.class;
  }
 else   if (value instanceof Boolean) {
    return Boolean.class;
  }
 else   if (value instanceof Byte) {
    return Byte.class;
  }
 else   if (value instanceof Short) {
    return Short.class;
  }
 else   if (value instanceof Integer) {
    return Integer.class;
  }
 else   if (value instanceof Long) {
    return Long.class;
  }
 else   if (value instanceof Float) {
    return Float.class;
  }
 else   if (value instanceof Double) {
    return Double.class;
  }
 else   if (value instanceof Date) {
    return Date.class;
  }
 else   if (value instanceof Geoshape) {
    return Geoshape.class;
  }
 else   if (value instanceof UUID) {
    return UUID.class;
  }
 else {
    return Object.class;
  }
}
