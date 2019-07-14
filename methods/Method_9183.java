private void updateInlineButton(){
  if (inlineButton == null) {
    return;
  }
  inlineButton.setImageResource(isInline ? R.drawable.ic_goinline : R.drawable.ic_outinline);
  inlineButton.setVisibility(videoPlayer.isPlayerPrepared() ? VISIBLE : GONE);
  if (isInline) {
    inlineButton.setLayoutParams(LayoutHelper.createFrame(40,40,Gravity.RIGHT | Gravity.TOP));
  }
 else {
    inlineButton.setLayoutParams(LayoutHelper.createFrame(56,50,Gravity.RIGHT | Gravity.TOP));
  }
}
