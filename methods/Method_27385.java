private void highlightUrl(TextView textView,BetterLinkMovementExtended.ClickableSpanWithText spanWithText,Spannable text){
  if (!this.isUrlHighlighted) {
    this.isUrlHighlighted=true;
    int spanStart=text.getSpanStart(spanWithText.span());
    int spanEnd=text.getSpanEnd(spanWithText.span());
    Selection.removeSelection(text);
    text.setSpan(new BackgroundColorSpan(textView.getHighlightColor()),spanStart,spanEnd,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
    textView.setText(text);
    Selection.setSelection(text,spanStart,spanEnd);
  }
}
