@NonNull @Override public CharSequence format(@NonNull CharSequence charSequence,int lineNumber){
  if (!(charSequence instanceof SpannableStringBuilder)) {
    return charSequence;
  }
  SpannableStringBuilder ssb=(SpannableStringBuilder)charSequence;
  String text=charSequence.toString();
  List<Pair<Integer,Integer>> list=TextHelper.find(text,SyntaxKey.KEY_CODE_BLOCK);
  for (int i=list.size() - 1; i >= 0; i--) {
    Pair<Integer,Integer> pair=list.get(i);
    int start=pair.first;
    int end=pair.second;
    List<Integer> middleList=TextHelper.getNewLineCharPosition(ssb,start,end);
    String language="";
    if (middleList.size() > 0) {
      language=ssb.subSequence(TextHelper.safePosition(start,ssb),TextHelper.safePosition(middleList.get(0),ssb)).toString().replace(SyntaxKey.KEY_CODE_BLOCK,"").replace("\n","");
    }
    int current=middleList.get(0) + 1;
    for (int j=1; j < middleList.size(); j++) {
      int position=middleList.get(j);
      if (position == current) {
        ssb.replace(position - 1,position," ");
      }
      ssb.setSpan(new MDCodeBlockSpan(mBackgroundColor,language,(j == 1 ? true : false),(j == middleList.size() - 1 ? true : false),ssb.subSequence(TextHelper.safePosition(current,ssb),TextHelper.safePosition(position,ssb)).toString()),current,position,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      SyntaxUtils.marginSSBLeft(ssb,mIndentedSize,current,position);
      current=position + 1;
    }
    if (!TextUtils.equals("",language)) {
      mPrettifyHighLighter.highLight(language,ssb,start,end);
    }
 else {
      current=middleList.get(0) + 1;
      for (int j=1; j < middleList.size(); j++) {
        int position=middleList.get(j);
        ssb.setSpan(new ForegroundColorSpan(mTextColor),current,position,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        current=position + 1;
      }
    }
    ssb.delete(end,end + SyntaxKey.KEY_CODE_BLOCK.length() + (end + SyntaxKey.KEY_CODE_BLOCK.length() >= ssb.length() ? 0 : 1));
    ssb.delete(start,TextHelper.findNextNewLineChar(ssb,start) + 1);
  }
  return ssb;
}
