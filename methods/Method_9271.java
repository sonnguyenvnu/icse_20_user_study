@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen && !backward && emojiText != null) {
    emojiTextView.setText(Emoji.replaceEmoji(emojiText,emojiTextView.getPaint().getFontMetricsInt(),AndroidUtilities.dp(32),false));
  }
}
