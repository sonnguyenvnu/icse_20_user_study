private void validatePlainReturnType(Class<?> commandReturnType,Class<?> fallbackReturnType,Method commandMethod,Method fallbackMethod){
  if (!commandReturnType.isAssignableFrom(fallbackReturnType)) {
    throw new FallbackDefinitionException(createErrorMsg(commandMethod,fallbackMethod,"Fallback method '" + fallbackMethod + "' must return: " + commandReturnType + " or its subclass"));
  }
}
