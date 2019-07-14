private static String parseStringAttr(String line,Pattern pattern,Map<String,String> variableDefinitions) throws ParserException {
  String value=parseOptionalStringAttr(line,pattern,variableDefinitions);
  if (value != null) {
    return value;
  }
 else {
    throw new ParserException("Couldn't match " + pattern.pattern() + " in " + line);
  }
}
