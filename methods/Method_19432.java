private boolean highlightOffsetsValid(CharSequence text,int highlightStart,int highlightEnd){
  return highlightStart >= 0 && highlightEnd <= text.length() && highlightStart < highlightEnd;
}
