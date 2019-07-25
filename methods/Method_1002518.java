private static <T>T coerce(String key,Object value,Class<T> valueClass){
  if (value == null) {
    return null;
  }
  if (!valueClass.isInstance(value)) {
    throw new IllegalArgumentException("Property " + key + " is of type " + value.getClass().getName() + " but must be " + valueClass.getName());
  }
  return valueClass.cast(value);
}
