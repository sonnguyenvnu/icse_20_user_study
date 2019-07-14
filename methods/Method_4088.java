private int getMinEnd(int def){
  int minEnd=mSpans[0].getEndLine(def);
  for (int i=1; i < mSpanCount; i++) {
    final int spanEnd=mSpans[i].getEndLine(def);
    if (spanEnd < minEnd) {
      minEnd=spanEnd;
    }
  }
  return minEnd;
}
