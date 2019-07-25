@Override SpannableStringBuilder format(@NonNull SpannableStringBuilder ssb,int lineNumber){
  SyntaxUtils.setTodoOrDoneClick(SyntaxKey.KEY_TODO_HYPHEN.length(),ssb,this);
  MDTodoSpan mdTodoSpan=new MDTodoSpan(mTodoColor,lineNumber);
  ssb.setSpan(mdTodoSpan,0,ssb.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  return ssb;
}
