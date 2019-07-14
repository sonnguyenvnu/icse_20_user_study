private void showPopup(int show,int contentType){
  if (show == 1) {
    if (contentType == 0 && emojiView == null) {
      if (parentActivity == null) {
        return;
      }
      createEmojiView();
    }
    View currentView=null;
    if (contentType == 0) {
      emojiView.setVisibility(VISIBLE);
      emojiViewVisible=true;
      if (botKeyboardView != null && botKeyboardView.getVisibility() != GONE) {
        botKeyboardView.setVisibility(GONE);
      }
      currentView=emojiView;
    }
 else     if (contentType == 1) {
      if (emojiView != null && emojiView.getVisibility() != GONE) {
        emojiView.setVisibility(GONE);
        emojiViewVisible=false;
      }
      botKeyboardView.setVisibility(VISIBLE);
      currentView=botKeyboardView;
    }
    currentPopupContentType=contentType;
    if (keyboardHeight <= 0) {
      keyboardHeight=MessagesController.getGlobalEmojiSettings().getInt("kbd_height",AndroidUtilities.dp(200));
    }
    if (keyboardHeightLand <= 0) {
      keyboardHeightLand=MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3",AndroidUtilities.dp(200));
    }
    int currentHeight=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight;
    if (contentType == 1) {
      currentHeight=Math.min(botKeyboardView.getKeyboardHeight(),currentHeight);
    }
    if (botKeyboardView != null) {
      botKeyboardView.setPanelHeight(currentHeight);
    }
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)currentView.getLayoutParams();
    layoutParams.height=currentHeight;
    currentView.setLayoutParams(layoutParams);
    if (!AndroidUtilities.isInMultiwindow) {
      AndroidUtilities.hideKeyboard(messageEditText);
    }
    if (sizeNotifierLayout != null) {
      emojiPadding=currentHeight;
      sizeNotifierLayout.requestLayout();
      setEmojiButtonImage(true,true);
      updateBotButton();
      onWindowSizeChanged();
    }
  }
 else {
    if (emojiButton != null) {
      setEmojiButtonImage(false,true);
    }
    currentPopupContentType=-1;
    if (emojiView != null) {
      emojiViewVisible=false;
      if (AndroidUtilities.usingHardwareInput || AndroidUtilities.isInMultiwindow) {
        emojiView.setVisibility(GONE);
      }
    }
    if (botKeyboardView != null) {
      botKeyboardView.setVisibility(GONE);
    }
    if (sizeNotifierLayout != null) {
      if (show == 0) {
        emojiPadding=0;
      }
      sizeNotifierLayout.requestLayout();
      onWindowSizeChanged();
    }
    updateBotButton();
  }
  if (stickersTabOpen || emojiTabOpen) {
    checkSendButton(true);
  }
  if (stickersExpanded && show != 1) {
    setStickersExpanded(false,false,false);
  }
}
