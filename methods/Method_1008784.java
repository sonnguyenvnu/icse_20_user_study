private String capitalize(String key){
  if (key == null) {
    throw new IllegalArgumentException("Key can't be null.");
  }
  if (key.length() == 0) {
    return "";
  }
  char firstUpper=Character.toUpperCase(key.charAt(0));
  if (key.length() == 1) {
    return Character.toString(firstUpper);
  }
  String rest=key.substring(1);
  return firstUpper + rest;
}
