@Override public Method createFrom(String valueString) throws IllegalArgumentException {
  return METHOD_PARSER.valueOf(valueString);
}
