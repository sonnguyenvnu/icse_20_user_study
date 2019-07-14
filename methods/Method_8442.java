public void setAllowStickersAndGifs(boolean value,boolean value2){
  if ((allowStickers != value || allowGifs != value2) && emojiView != null) {
    if (emojiViewVisible) {
      hidePopup(false);
    }
    sizeNotifierLayout.removeView(emojiView);
    emojiView=null;
  }
  allowStickers=value;
  allowGifs=value2;
  setEmojiButtonImage(false,!isPaused);
}
