@Override public boolean matches(TypeDescriptor sourceType,TypeDescriptor targetType){
  return !sourceType.equals(URI_TYPE) ? false : repositories.getRepositoryInformationFor(targetType.getType()).isPresent();
}
