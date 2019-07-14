private void applyTextStyleToSelection(TypefaceSpan span){
  int start;
  int end;
  if (selectionStart >= 0 && selectionEnd >= 0) {
    start=selectionStart;
    end=selectionEnd;
    selectionStart=selectionEnd=-1;
  }
 else {
    start=getSelectionStart();
    end=getSelectionEnd();
  }
  Editable editable=getText();
  CharacterStyle spans[]=editable.getSpans(start,end,CharacterStyle.class);
  if (spans != null && spans.length > 0) {
    for (int a=0; a < spans.length; a++) {
      CharacterStyle oldSpan=spans[a];
      int spanStart=editable.getSpanStart(oldSpan);
      int spanEnd=editable.getSpanEnd(oldSpan);
      editable.removeSpan(oldSpan);
      if (spanStart < start) {
        editable.setSpan(oldSpan,spanStart,start,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      if (spanEnd > end) {
        editable.setSpan(oldSpan,end,spanEnd,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
  }
  if (span != null) {
    editable.setSpan(span,start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  if (delegate != null) {
    delegate.onSpansChanged();
  }
}
