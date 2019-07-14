public boolean canAcceptType(Class<?> candidate){
  return type.isAssignableFrom(candidate) || isAssignableViaTypeConversion(type,candidate);
}
