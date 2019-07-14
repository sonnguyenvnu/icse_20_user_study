private boolean isAssignableViaTypeConversion(Class<?> targetType,Class<?> candidate){
  if (CONVERTABLE_TYPES_MAP.containsKey(candidate)) {
    Class<?> wrapperClass=CONVERTABLE_TYPES_MAP.get(candidate);
    return targetType.isAssignableFrom(wrapperClass);
  }
 else {
    return false;
  }
}
