public Object[] getMethodArguments() throws CouldNotGenerateValueException {
  return getActualValues(getConstructorParameterCount(),assigned.size());
}
