@NonNull @Override public CharSequence format(@NonNull CharSequence charSequence,int lineNumber){
  if (!(charSequence instanceof SpannableStringBuilder)) {
    return charSequence;
  }
  SpannableStringBuilder ssb=(SpannableStringBuilder)charSequence;
  int currentLineIndex=0;
  String[] lines=charSequence.toString().split("\n");
  ArrayList<NestedOrderListBean> list=new ArrayList<>(lines.length);
  for (int i=0; i < lines.length; i++) {
    int nested=calculateNested(lines[i]);
    if (nested < 0) {
      list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    if (SyntaxUtils.existCodeBlockSpan(ssb,currentLineIndex,currentLineIndex + (lines[i]).length())) {
      list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    int number=calculateNumber(lines[i],nested);
    if (i - 1 < 0 || i - 1 >= list.size()) {
      if (nested == 0) {
        list.add(new NestedOrderListBean(currentLineIndex,true,lines[i],0,1,number));
      }
 else {
        list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
      }
      currentLineIndex+=(lines[i] + "\n").length();
      continue;
    }
    NestedOrderListBean previousBean=list.get(i - 1);
    if (previousBean != null && previousBean.isRegular && (nested <= previousBean.nested + 1)) {
      if (nested == previousBean.nested) {
        list.add(new NestedOrderListBean(currentLineIndex,true,lines[i],nested,previousBean.number + 1,number));
      }
 else       if (nested == previousBean.nested + 1) {
        list.add(new NestedOrderListBean(currentLineIndex,true,lines[i],nested,1,number));
      }
 else {
        for (int j=i - 2; j >= 0; j--) {
          NestedOrderListBean previousSameNestedBean=list.get(j);
          if (previousSameNestedBean != null && previousSameNestedBean.isRegular && previousSameNestedBean.nested == nested) {
            list.add(new NestedOrderListBean(currentLineIndex,true,lines[i],nested,previousSameNestedBean.number + 1,number));
            break;
          }
 else           if (previousSameNestedBean == null || !previousSameNestedBean.isRegular) {
            list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
            break;
          }
        }
        NestedOrderListBean bean=list.get(i);
        if (bean == null) {
          list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
        }
      }
    }
 else     if (previousBean != null && !previousBean.isRegular && nested == 0) {
      list.add(new NestedOrderListBean(currentLineIndex,true,lines[i],nested,1,number));
    }
 else {
      list.add(new NestedOrderListBean(currentLineIndex,false,lines[i],-1,-1,-1));
    }
    currentLineIndex+=(lines[i] + "\n").length();
  }
  for (int i=list.size() - 1; i >= 0; i--) {
    NestedOrderListBean bean=list.get(i);
    if (bean != null && bean.isRegular) {
      setSSB(bean.nested,bean.start,bean.line,ssb,bean.number,bean.originalNumber);
    }
  }
  return ssb;
}
