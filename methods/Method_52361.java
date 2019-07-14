private void init(){
  if (pattern == null) {
    String stringPattern=super.getProperty(REGEX_PROPERTY);
    if (stringPattern != null && stringPattern.length() > 0) {
      pattern=Pattern.compile(stringPattern);
    }
 else {
      throw new IllegalArgumentException("Must provide a value for the '" + PROPERTY_NAME + "' property.");
    }
  }
}
