boolean areAllEndsEqual(){
  int end=mSpans[0].getEndLine(Span.INVALID_LINE);
  for (int i=1; i < mSpanCount; i++) {
    if (mSpans[i].getEndLine(Span.INVALID_LINE) != end) {
      return false;
    }
  }
  return true;
}
