boolean areAllStartsEqual(){
  int start=mSpans[0].getStartLine(Span.INVALID_LINE);
  for (int i=1; i < mSpanCount; i++) {
    if (mSpans[i].getStartLine(Span.INVALID_LINE) != start) {
      return false;
    }
  }
  return true;
}
