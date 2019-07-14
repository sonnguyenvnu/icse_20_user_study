public boolean canPotentiallyAcceptType(Class<?> candidate){
  return candidate.isAssignableFrom(type) || isAssignableViaTypeConversion(candidate,type) || canAcceptType(candidate);
}
