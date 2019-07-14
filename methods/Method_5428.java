private static Pattern compileBooleanAttrPattern(String attribute){
  return Pattern.compile(attribute + "=(" + BOOLEAN_FALSE + "|" + BOOLEAN_TRUE + ")");
}
