private void updateRemainingSpans(Span span,int layoutDir,int targetLine){
  final int deletedSize=span.getDeletedSize();
  if (layoutDir == LayoutState.LAYOUT_START) {
    final int line=span.getStartLine();
    if (line + deletedSize <= targetLine) {
      mRemainingSpans.set(span.mIndex,false);
    }
  }
 else {
    final int line=span.getEndLine();
    if (line - deletedSize >= targetLine) {
      mRemainingSpans.set(span.mIndex,false);
    }
  }
}
