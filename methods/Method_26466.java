static String replacementTypeVarName(Name name,Set<String> superTypeVars){
  String baseName=name.toString();
  int typeVarNum=2;
  Matcher matcher=TRAILING_DIGIT_EXTRACTOR.matcher(name);
  if (matcher.matches()) {
    baseName=matcher.group(1);
    typeVarNum=Integer.parseInt(matcher.group(2)) + 1;
  }
  String replacementName;
  while (superTypeVars.contains(replacementName=baseName + typeVarNum)) {
    typeVarNum++;
  }
  return replacementName;
}
