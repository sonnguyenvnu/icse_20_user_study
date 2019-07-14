private void assertParameterNotBlank(String parameter,ErrorType type){
  if (null == parameter || parameter.isEmpty()) {
    throw new GrantTokenException(type);
  }
}
