private static String firstLetterReplacementName(String name,List<String> superTypeVars){
  String firstLetterOfBase=Character.toString(name.charAt(0));
  int typeVarNum=2;
  boolean first=true;
  Matcher matcher=TRAILING_DIGIT_EXTRACTOR.matcher(name);
  if (matcher.matches()) {
    name=matcher.group(1);
    typeVarNum=Integer.parseInt(matcher.group(2)) + 1;
  }
  String replacementName="";
  for (  String superTypeVar : superTypeVars) {
    if (superTypeVar.equals(name)) {
      if (typeVarNum == 2 && first) {
        return firstLetterOfBase;
      }
      break;
    }
 else     if (superTypeVar.charAt(0) == name.charAt(0)) {
      if (!first) {
        typeVarNum++;
      }
 else {
        first=false;
      }
      replacementName=firstLetterOfBase + typeVarNum;
    }
  }
  while (superTypeVars.contains(replacementName)) {
    typeVarNum++;
    replacementName=firstLetterOfBase + typeVarNum;
  }
  return replacementName;
}
