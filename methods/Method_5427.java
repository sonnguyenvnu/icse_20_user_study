private static @PolyNull String parseOptionalStringAttr(String line,Pattern pattern,@PolyNull String defaultValue,Map<String,String> variableDefinitions){
  Matcher matcher=pattern.matcher(line);
  String value=matcher.find() ? matcher.group(1) : defaultValue;
  return variableDefinitions.isEmpty() || value == null ? value : replaceVariableReferences(value,variableDefinitions);
}
