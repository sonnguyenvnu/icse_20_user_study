private static boolean parseBoolean(String value){
  if ("true".equalsIgnoreCase(value)) {
    return true;
  }
  if ("false".equalsIgnoreCase(value)) {
    return false;
  }
  throw new IllegalArgumentException(String.format("Error Prone flag value %s could not be parsed as a boolean.",value));
}
