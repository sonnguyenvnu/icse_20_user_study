@NonNull @Override public List<EditToken> format(@NonNull Editable editable){
  List<EditToken> editTokenList=new ArrayList<>();
  List<Pair<Integer,Integer>> list=TextHelper.find(editable.toString(),SyntaxKey.KEY_CODE_BLOCK);
  for (int i=list.size() - 1; i >= 0; i--) {
    Pair<Integer,Integer> pair=list.get(i);
    int start=pair.first;
    int end=pair.second;
    List<Integer> middleList=TextHelper.getNewLineCharPosition((SpannableStringBuilder)editable,start,end);
    int current=start;
    MDCodeBlockSpan parentSpan=null;
    for (int j=0; j < middleList.size(); j++) {
      int position=middleList.get(j);
      MDCodeBlockSpan mdCodeBlockSpan=new MDCodeBlockSpan(mColor);
      if (position == current) {
        editTokenList.add(new EditToken(mdCodeBlockSpan,position - 1,position + 1,j == 0 ? Spannable.SPAN_EXCLUSIVE_INCLUSIVE : Spannable.SPAN_INCLUSIVE_INCLUSIVE));
      }
 else {
        editTokenList.add(new EditToken(mdCodeBlockSpan,current,position,j == 0 ? Spannable.SPAN_EXCLUSIVE_INCLUSIVE : Spannable.SPAN_INCLUSIVE_INCLUSIVE));
      }
      if (parentSpan != null) {
        parentSpan.setNext(mdCodeBlockSpan);
      }
      parentSpan=mdCodeBlockSpan;
      current=position + 1;
    }
    MDCodeBlockSpan mdCodeBlockSpan=new MDCodeBlockSpan(mColor);
    editTokenList.add(new EditToken(mdCodeBlockSpan,end,end + SyntaxKey.KEY_CODE_BLOCK.length() + (end + SyntaxKey.KEY_CODE_BLOCK.length() >= editable.length() ? 0 : 1),Spannable.SPAN_INCLUSIVE_EXCLUSIVE));
    if (parentSpan != null) {
      parentSpan.setNext(mdCodeBlockSpan);
    }
  }
  return editTokenList;
}
