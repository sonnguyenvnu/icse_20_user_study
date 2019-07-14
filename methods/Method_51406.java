private static List<Method> methodsFrom(String valueString){
  return ValueParserConstants.parsePrimitives(valueString,MULTI_VALUE_DELIMITER,METHOD_PARSER);
}
