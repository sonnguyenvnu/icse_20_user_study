@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  List<EditToken> editTokenList=new ArrayList<>();
  Pattern p=Pattern.compile(PATTERN,Pattern.MULTILINE);
  StringBuilder content=new StringBuilder(editable);
  Matcher m=p.matcher(content);
  List<String> matchList=new ArrayList<>();
  while (m.find()) {
    matchList.add(m.group());
  }
  for (  String match : matchList) {
    int index=findTrueIndex(match,content);
    int length=match.length();
    int nested=calculateNested(match);
    int number=calculateNumber(match,nested);
    editTokenList.add(new EditToken(new MDOrderListSpan(10,nested,number),index,index + length,Spannable.SPAN_INCLUSIVE_INCLUSIVE));
    content.replace(index,index + length,TextHelper.getPlaceHolder(match));
  }
  return editTokenList;
}
