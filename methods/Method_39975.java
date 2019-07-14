public static boolean validate(final Object value,final int min,final int max){
  if (value == null) {
    return true;
  }
  if (value instanceof Collection) {
    final int size=((Collection<?>)value).size();
    return size >= min && size <= max;
  }
  if (value instanceof Map) {
    final int size=((Map<?,?>)value).size();
    return size >= min && size <= max;
  }
  if (value.getClass().isArray()) {
    final int size=Array.getLength(value);
    return size >= min && size <= max;
  }
  return false;
}
