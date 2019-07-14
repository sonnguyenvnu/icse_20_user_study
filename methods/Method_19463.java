@GetExtraAccessibilityNodeAt static int getExtraAccessibilityNodeAt(int x,int y,@Prop(resType=ResType.STRING) CharSequence text,@FromBoundsDefined Layout textLayout,@FromBoundsDefined ClickableSpan[] clickableSpans){
  final Spanned spanned=(Spanned)text;
  for (int i=0; i < clickableSpans.length; i++) {
    final ClickableSpan span=clickableSpans[i];
    final int start=spanned.getSpanStart(span);
    final int end=spanned.getSpanEnd(span);
    textLayout.getSelectionPath(start,end,sTempPath);
    sTempPath.computeBounds(sTempRectF,true);
    if (sTempRectF.contains(x,y)) {
      return i;
    }
  }
  return INVALID_ID;
}
