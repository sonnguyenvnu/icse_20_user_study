private static @Nullable String parseOptionalStringAttr(String line,Pattern pattern,Map<String,String> variableDefinitions){
  return parseOptionalStringAttr(line,pattern,null,variableDefinitions);
}
