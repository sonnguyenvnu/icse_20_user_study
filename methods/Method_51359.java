@Override public Boolean createFrom(String propertyString) throws IllegalArgumentException {
  return BOOLEAN_PARSER.valueOf(propertyString);
}
