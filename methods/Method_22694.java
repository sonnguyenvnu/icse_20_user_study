public int getInteger(String attribute){
  String value=get(attribute);
  if (value == null) {
    System.err.println("Integer not found: " + attribute);
    return 0;
  }
  return Integer.parseInt(value);
}
