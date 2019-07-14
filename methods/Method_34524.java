private void validateParametrizedType(Type commandReturnType,Type fallbackReturnType,Method commandMethod,Method fallbackMethod){
  if (!commandReturnType.equals(fallbackReturnType)) {
    throw new FallbackDefinitionException(createErrorMsg(commandMethod,fallbackMethod,"Fallback method '" + fallbackMethod + "' must return: " + commandReturnType + " or its subclass"));
  }
}
