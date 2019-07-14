@Override public void onSizeChanged(int height,boolean isWidthGreater){
  if (searchingType != 0) {
    lastSizeChangeValue1=height;
    lastSizeChangeValue2=isWidthGreater;
    keyboardVisible=height > 0;
    return;
  }
  if (height > AndroidUtilities.dp(50) && keyboardVisible && !AndroidUtilities.isInMultiwindow) {
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
    if (currentPopupContentType == 1 && !botKeyboardView.isFullSize()) {
      newHeight=Math.min(botKeyboardView.getKeyboardHeight(),newHeight);
    }
    View currentView=null;
    if (currentPopupContentType == 0) {
      currentView=emojiView;
    }
 else     if (currentPopupContentType == 1) {
      currentView=botKeyboardView;
    }
    if (botKeyboardView != null) {
      botKeyboardView.setPanelHeight(newHeight);
    }
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)currentView.getLayoutParams();
    if (!closeAnimationInProgress && (layoutParams.width != AndroidUtilities.displaySize.x || layoutParams.height != newHeight) && !stickersExpanded) {
      layoutParams.width=AndroidUtilities.displaySize.x;
      layoutParams.height=newHeight;
      currentView.setLayoutParams(layoutParams);
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
    showPopup(0,currentPopupContentType);
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
