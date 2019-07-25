@NonNull @Override public CharSequence format(@NonNull CharSequence charSequence,int lineNumber){
  if (!(charSequence instanceof SpannableStringBuilder)) {
    return charSequence;
  }
  SpannableStringBuilder ssb=(SpannableStringBuilder)charSequence;
  int currentLineIndex=0;
  String[] lines=charSequence.toString().split("\n");
  ArrayList<ListBean> list=new ArrayList<>(lines.length);
  SparseArray<MDBaseListSpan> listSpanByLineArray=new SparseArray<>();
  for (int i=0; i < lines.length; i++) {
    String line=lines[i];
    if (checkOrderLegal(line.trim())) {
      currentLineIndex=formatOrder(ssb,line,list,i,currentLineIndex);
    }
 else     if (checkUnorderLegal(line.trim())) {
      currentLineIndex=formatUnorder(ssb,line,list,i,currentLineIndex);
    }
 else {
      list.add(new ListBean(currentLineIndex,false,lines[i],-1,-1,-1));
      currentLineIndex+=(line + "\n").length();
    }
  }
  for (int i=list.size() - 1; i >= 0; i--) {
    ListBean bean=list.get(i);
    if (bean != null && bean.isRegular) {
      MDBaseListSpan span=null;
      if (bean.isOrder) {
        span=setOrderSpan(bean.nested,bean.start,bean.line,ssb,bean.number,bean.originalNumber);
      }
 else {
        span=setUnorderSpan(bean.nested,bean.start,bean.line,bean.type,ssb,mUnorderColor);
      }
      listSpanByLineArray.put(i,span);
    }
  }
  int previousLineIndex=-1;
  MDBaseListSpan previousSpan=null;
  for (int i=0; i < listSpanByLineArray.size(); i++) {
    int lineIndex=listSpanByLineArray.keyAt(i);
    MDBaseListSpan span=listSpanByLineArray.get(lineIndex);
    if (previousSpan != null && previousLineIndex + 1 == lineIndex) {
      span.setParent(span);
    }
    previousLineIndex=lineIndex;
    previousSpan=span;
  }
  return ssb;
}
