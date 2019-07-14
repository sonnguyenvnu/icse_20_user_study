@Override protected String valueErrorFor(String value){
  if (value == null) {
    return "Missing value";
  }
  if (containsDelimiter(value)) {
    return "Value cannot contain the '" + multiValueDelimiter() + "' character";
  }
  return null;
}
