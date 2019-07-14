private void showPopup(int show){
  if (show == 1) {
    if (emojiView == null) {
      createEmojiView();
    }
    emojiView.setVisibility(VISIBLE);
    emojiViewVisible=true;
    View currentView=emojiView;
    if (keyboardHeight <= 0) {
      if (AndroidUtilities.isTablet()) {
        keyboardHeight=AndroidUtilities.dp(150);
      }
 else {
        keyboardHeight=MessagesController.getGlobalEmojiSettings().getInt("kbd_height",AndroidUtilities.dp(200));
      }
    }
    if (keyboardHeightLand <= 0) {
      if (AndroidUtilities.isTablet()) {
        keyboardHeightLand=AndroidUtilities.dp(150);
      }
 else {
        keyboardHeightLand=MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3",AndroidUtilities.dp(200));
      }
    }
    int currentHeight=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight;
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)currentView.getLayoutParams();
    layoutParams.height=currentHeight;
    currentView.setLayoutParams(layoutParams);
    if (!AndroidUtilities.isInMultiwindow && !AndroidUtilities.isTablet()) {
      AndroidUtilities.hideKeyboard(editText);
    }
    if (sizeNotifierLayout != null) {
      emojiPadding=currentHeight;
      sizeNotifierLayout.requestLayout();
      emojiButton.setImageResource(R.drawable.input_keyboard);
      onWindowSizeChanged();
    }
  }
 else {
    if (emojiButton != null) {
      if (currentStyle == STYLE_FRAGMENT) {
        emojiButton.setImageResource(R.drawable.smiles_tab_smiles);
      }
 else {
        emojiButton.setImageResource(R.drawable.input_smile);
      }
    }
    if (emojiView != null) {
      emojiViewVisible=false;
      if (AndroidUtilities.usingHardwareInput || AndroidUtilities.isInMultiwindow) {
        emojiView.setVisibility(GONE);
      }
    }
    if (sizeNotifierLayout != null) {
      if (show == 0) {
        emojiPadding=0;
      }
      sizeNotifierLayout.requestLayout();
      onWindowSizeChanged();
    }
  }
}
