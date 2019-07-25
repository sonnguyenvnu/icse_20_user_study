@NonNull @Override @SuppressLint("WrongConstant") public List<EditToken> format(@NonNull Editable editable){
  List<EditToken> editTokenList=new ArrayList<>();
  List<String> matchList0=new ArrayList<>();
  List<String> matchList1=new ArrayList<>();
  List<String> matchList2=new ArrayList<>();
  StringBuilder content=new StringBuilder(editable);
  replaceTodo(content);
  Pattern p0=Pattern.compile("^( *)(\\+ )(.*?)$",Pattern.MULTILINE);
  Matcher m0=p0.matcher(content);
  while (m0.find()) {
    matchList0.add(m0.group());
  }
  Pattern p1=Pattern.compile("^( *)(\\- )(.*?)$",Pattern.MULTILINE);
  Matcher m1=p1.matcher(content);
  while (m1.find()) {
    matchList1.add(m1.group());
  }
  Pattern p2=Pattern.compile("^( *)(\\* )(.*?)$",Pattern.MULTILINE);
  Matcher m2=p2.matcher(content);
  while (m2.find()) {
    matchList2.add(m2.group());
  }
  replace(matchList0,MDUnOrderListSpan.TYPE_KEY_2,content,editTokenList);
  replace(matchList1,MDUnOrderListSpan.TYPE_KEY_1,content,editTokenList);
  replace(matchList2,MDUnOrderListSpan.TYPE_KEY_0,content,editTokenList);
  return editTokenList;
}
