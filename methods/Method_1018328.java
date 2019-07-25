@Override public boolean supports(Class<?> delimiter){
  return domainType.isAssignableFrom(delimiter);
}
