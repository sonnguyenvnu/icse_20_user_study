@Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  SyntaxUtils.setTodoOrDoneClick(SyntaxKey.KEY_TODO_DONE_0.length(),ssb,this);
  ssb.setSpan(new MDTodoDoneSpan(mDoneColor,lineNumber),0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  return ssb;
}
