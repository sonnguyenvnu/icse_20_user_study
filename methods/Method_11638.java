public static Class<?> detectBasicClass(Class<?> type){
  if (type.equals(Integer.TYPE) || type.equals(Integer.class)) {
    return Integer.class;
  }
 else   if (type.equals(Long.TYPE) || type.equals(Long.class)) {
    return Long.class;
  }
 else   if (type.equals(Double.TYPE) || type.equals(Double.class)) {
    return Double.class;
  }
 else   if (type.equals(Float.TYPE) || type.equals(Float.class)) {
    return Float.class;
  }
 else   if (type.equals(Short.TYPE) || type.equals(Short.class)) {
    return Short.class;
  }
 else   if (type.equals(Character.TYPE) || type.equals(Character.class)) {
    return Character.class;
  }
 else   if (type.equals(Byte.TYPE) || type.equals(Byte.class)) {
    return Byte.class;
  }
 else   if (type.equals(Boolean.TYPE) || type.equals(Boolean.class)) {
    return Boolean.class;
  }
  return type;
}
