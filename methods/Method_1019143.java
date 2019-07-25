@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  List<EditToken> editTokenList=new ArrayList<>();
  StringBuilder content=new StringBuilder(editable);
  Matcher m=Pattern.compile(PATTERN,Pattern.MULTILINE).matcher(content);
  List<String> matchList=new ArrayList<>();
  Map<String,Integer> specialMap=new HashMap<>();
  while (m.find()) {
    String matchString=m.group();
    if (matchSpecial(matchString)) {
      addSpecial(matchString,specialMap);
    }
 else {
      matchList.add(matchString);
    }
  }
  Matcher m2=Pattern.compile(PATTERN_WITH_CENTER_ALIGN,Pattern.MULTILINE).matcher(content);
  while (m2.find()) {
    String matchString=m2.group();
    if (matchSpecial(matchString)) {
      addSpecial(matchString,specialMap);
    }
 else {
      matchList.add(matchString);
    }
  }
  for (  String match : matchList) {
    replace(content,match,editTokenList);
  }
  replaceSpecial(specialMap,content,editTokenList);
  return editTokenList;
}
