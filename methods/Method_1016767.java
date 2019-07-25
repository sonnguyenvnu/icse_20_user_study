@Override public Span subspan(int firstToken,int lastToken){
  StringSpan firstSpan=(StringSpan)get(firstToken);
  int startIdx=firstSpan.getStartIdx();
  int endIdx;
  if (lastToken > size()) {
    endIdx=document.length();
  }
 else {
    StringSpan lastSpan=(StringSpan)get(lastToken - 1);
    endIdx=lastSpan.getEndIdx();
  }
  return new StringSpan(document,startIdx,endIdx);
}
