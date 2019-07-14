private void dispatchUrlClick(TextView textView,BetterLinkMovementExtended.ClickableSpanWithText spanWithText){
  String spanUrl=spanWithText.text();
  boolean handled=this.onLinkClickListener != null && this.onLinkClickListener.onClick(textView,spanUrl);
  if (!handled) {
    spanWithText.span().onClick(textView);
  }
}
