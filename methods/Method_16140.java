@Override public boolean matches(Method method,Class<?> aClass){
  return loggerParsers.stream().anyMatch(parser -> parser.support(aClass,method));
}
