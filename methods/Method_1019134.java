@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  List<EditToken> editTokenList=new ArrayList<>();
  StringBuilder content=new StringBuilder(editable);
  Matcher m=Pattern.compile(PATTERN,Pattern.MULTILINE).matcher(content);
  List<String> matchList=new ArrayList<>();
  while (m.find()) {
    matchList.add(m.group());
  }
  for (  String match : matchList) {
    int nested=calculateNested(match);
    if (nested == 0) {
      return editTokenList;
    }
    int index=content.indexOf(match);
    int length=match.length();
    editTokenList.add(new EditToken(new MDQuoteSpan(mColor,nested),index,index + length));
    content.replace(index,index + length,TextHelper.getPlaceHolder(match));
  }
  return editTokenList;
}
