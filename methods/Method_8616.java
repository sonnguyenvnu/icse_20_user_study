@Override public void onSizeChanged(int height,boolean isWidthGreater){
  if (height > AndroidUtilities.dp(50) && keyboardVisible && !AndroidUtilities.isInMultiwindow && !AndroidUtilities.isTablet()) {
    if (isWidthGreater) {
      keyboardHeightLand=height;
      MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3",keyboardHeightLand).commit();
    }
 else {
      keyboardHeight=height;
      MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height",keyboardHeight).commit();
    }
  }
  if (isPopupShowing()) {
    int newHeight=isWidthGreater ? keyboardHeightLand : keyboardHeight;
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)emojiView.getLayoutParams();
    if (layoutParams.width != AndroidUtilities.displaySize.x || layoutParams.height != newHeight) {
      layoutParams.width=AndroidUtilities.displaySize.x;
      layoutParams.height=newHeight;
      emojiView.setLayoutParams(layoutParams);
      if (sizeNotifierLayout != null) {
        emojiPadding=layoutParams.height;
        sizeNotifierLayout.requestLayout();
        onWindowSizeChanged();
      }
    }
  }
  if (lastSizeChangeValue1 == height && lastSizeChangeValue2 == isWidthGreater) {
    onWindowSizeChanged();
    return;
  }
  lastSizeChangeValue1=height;
  lastSizeChangeValue2=isWidthGreater;
  boolean oldValue=keyboardVisible;
  keyboardVisible=height > 0;
  if (keyboardVisible && isPopupShowing()) {
    showPopup(0);
  }
  if (emojiPadding != 0 && !keyboardVisible && keyboardVisible != oldValue && !isPopupShowing()) {
    emojiPadding=0;
    sizeNotifierLayout.requestLayout();
  }
  if (keyboardVisible && waitingForKeyboardOpen) {
    waitingForKeyboardOpen=false;
    AndroidUtilities.cancelRunOnUIThread(openKeyboardRunnable);
  }
  onWindowSizeChanged();
}
