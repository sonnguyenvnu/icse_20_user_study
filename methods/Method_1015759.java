public static Object convert(String arg,Class<?> type){
  if (type == String.class)   return arg;
  if (type == boolean.class || type == Boolean.class)   return Boolean.valueOf(arg);
  if (type == byte.class || type == Byte.class)   return Byte.valueOf(arg);
  if (type == short.class || type == Short.class)   return Short.valueOf(arg);
  if (type == int.class || type == Integer.class)   return Integer.valueOf(arg);
  if (type == long.class || type == Long.class)   return Long.valueOf(arg);
  if (type == float.class || type == Float.class)   return Float.valueOf(arg);
  if (type == double.class || type == Double.class)   return Double.valueOf(arg);
  return arg;
}
