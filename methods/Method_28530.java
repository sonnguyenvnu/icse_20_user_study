public SpannableBuilder append(Object span){
  setSpan(span,length() - 1,length(),SPAN_EXCLUSIVE_EXCLUSIVE);
  return this;
}
