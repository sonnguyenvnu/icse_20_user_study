private void dispatchUrlLongClick(TextView textView,BetterLinkMovementExtended.ClickableSpanWithText spanWithText){
  String spanUrl=spanWithText.text();
  if (onLinkLongClickListener != null)   onLinkLongClickListener.onLongClick(textView,spanUrl);
}
