@NonNull @Override public CharSequence format(@NonNull CharSequence charSequence,int lineNumber){
  if (!(charSequence instanceof SpannableStringBuilder)) {
    return charSequence;
  }
  SpannableStringBuilder ssb=(SpannableStringBuilder)charSequence;
  String text=charSequence.toString();
  int currentLineIndex=0;
  String[] lines=text.split("\n");
  ArrayList<NestedUnOrderListBean> list=new ArrayList<>(lines.length);
  for (int i=0; i < lines.length; i++) {
    if (lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_HYPHEN) || lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_ASTERISK) || lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_2) || lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_3) || lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_4) || lines[i].startsWith(SyntaxKey.IGNORE_UNORDER_LIST_5)) {
      list.add(new NestedUnOrderListBean(currentLineIndex,false,lines[i],-1,0));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (SyntaxUtils.existCodeBlockSpan(ssb,currentLineIndex,currentLineIndex + (lines[i]).length())) {
      list.add(new NestedUnOrderListBean(currentLineIndex,false,lines[i],-1,0));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (lines[i].startsWith(SyntaxKey.KEY_UNORDER_LIST_ASTERISK)) {
      list.add(new NestedUnOrderListBean(currentLineIndex,true,lines[i],0,MDUnOrderListSpan.TYPE_KEY_2));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (lines[i].startsWith(SyntaxKey.KEY_UNORDER_LIST_PLUS)) {
      list.add(new NestedUnOrderListBean(currentLineIndex,true,lines[i],0,MDUnOrderListSpan.TYPE_KEY_0));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (lines[i].startsWith(SyntaxKey.KEY_UNORDER_LIST_HYPHEN)) {
      list.add(new NestedUnOrderListBean(currentLineIndex,true,lines[i],0,MDUnOrderListSpan.TYPE_KEY_1));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (!lines[i].startsWith(SyntaxKey.KEY_LIST_HEADER)) {
      list.add(new NestedUnOrderListBean(currentLineIndex,false,lines[i],-1,0));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    int nested=calculateNested(lines[i]);
    if (nested > 0) {
      if (i - 1 < 0 || i - 1 >= list.size()) {
        currentLineIndex+=(lines[i] + "\n").length();
        continue;
      }
      int type=calculateNestedType(lines[i]);
      NestedUnOrderListBean previousBean=list.get(i - 1);
      if (previousBean != null && previousBean.isRegular && (nested <= previousBean.nested + 1)) {
        list.add(new NestedUnOrderListBean(currentLineIndex,true,lines[i],nested,type));
      }
 else {
        list.add(new NestedUnOrderListBean(currentLineIndex,false,lines[i],-1,0));
      }
    }
    currentLineIndex+=(lines[i] + "\n").length();
  }
  for (int i=list.size() - 1; i >= 0; i--) {
    NestedUnOrderListBean bean=list.get(i);
    if (bean != null && bean.isRegular) {
      setSSB(bean.nested,bean.start,bean.line,bean.type,ssb);
    }
  }
  return ssb;
}
