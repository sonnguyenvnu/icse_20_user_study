public static <T>String beanToString(T value){
  if (value == null) {
    return null;
  }
  Class<?> clazz=value.getClass();
  if (clazz == int.class || clazz == Integer.class) {
    return "" + value;
  }
 else   if (clazz == String.class) {
    return (String)value;
  }
 else   if (clazz == long.class || clazz == Long.class) {
    return "" + value;
  }
 else {
    return JSON.toJSONString(value);
  }
}
