private void replace(List<String> matchList,int type,StringBuilder content,List<EditToken> editTokenList){
  for (  String match : matchList) {
    int index=content.indexOf(match);
    int length=match.length();
    int nested=calculateNested(match);
    editTokenList.add(new EditToken(new MDUnOrderListSpan(10,mColor,nested,type),index,index + length,Spannable.SPAN_INCLUSIVE_INCLUSIVE));
    content.replace(index,index + length,TextHelper.getPlaceHolder(match));
  }
}
