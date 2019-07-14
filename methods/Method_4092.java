/** 
 * Finds the span for the next view.
 */
private Span getNextSpan(LayoutState layoutState){
  final boolean preferLastSpan=preferLastSpan(layoutState.mLayoutDirection);
  final int startIndex, endIndex, diff;
  if (preferLastSpan) {
    startIndex=mSpanCount - 1;
    endIndex=-1;
    diff=-1;
  }
 else {
    startIndex=0;
    endIndex=mSpanCount;
    diff=1;
  }
  if (layoutState.mLayoutDirection == LayoutState.LAYOUT_END) {
    Span min=null;
    int minLine=Integer.MAX_VALUE;
    final int defaultLine=mPrimaryOrientation.getStartAfterPadding();
    for (int i=startIndex; i != endIndex; i+=diff) {
      final Span other=mSpans[i];
      int otherLine=other.getEndLine(defaultLine);
      if (otherLine < minLine) {
        min=other;
        minLine=otherLine;
      }
    }
    return min;
  }
 else {
    Span max=null;
    int maxLine=Integer.MIN_VALUE;
    final int defaultLine=mPrimaryOrientation.getEndAfterPadding();
    for (int i=startIndex; i != endIndex; i+=diff) {
      final Span other=mSpans[i];
      int otherLine=other.getStartLine(defaultLine);
      if (otherLine > maxLine) {
        max=other;
        maxLine=otherLine;
      }
    }
    return max;
  }
}
