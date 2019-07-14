private boolean checkSpanForGap(Span span){
  if (mShouldReverseLayout) {
    if (span.getEndLine() < mPrimaryOrientation.getEndAfterPadding()) {
      final View endView=span.mViews.get(span.mViews.size() - 1);
      final LayoutParams lp=span.getLayoutParams(endView);
      return !lp.mFullSpan;
    }
  }
 else   if (span.getStartLine() > mPrimaryOrientation.getStartAfterPadding()) {
    final View startView=span.mViews.get(0);
    final LayoutParams lp=span.getLayoutParams(startView);
    return !lp.mFullSpan;
  }
  return false;
}
