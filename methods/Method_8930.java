private void showPopup(int show){
  if (show == 1) {
    if (emojiView == null) {
      createEmojiView();
    }
    emojiView.setVisibility(VISIBLE);
    if (keyboardHeight <= 0) {
      keyboardHeight=MessagesController.getGlobalEmojiSettings().getInt("kbd_height",AndroidUtilities.dp(200));
    }
    if (keyboardHeightLand <= 0) {
      keyboardHeightLand=MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3",AndroidUtilities.dp(200));
    }
    int currentHeight=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y ? keyboardHeightLand : keyboardHeight;
    FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)emojiView.getLayoutParams();
    layoutParams.width=AndroidUtilities.displaySize.x;
    layoutParams.height=currentHeight;
    emojiView.setLayoutParams(layoutParams);
    if (!AndroidUtilities.isInMultiwindow && !forceFloatingEmoji) {
      AndroidUtilities.hideKeyboard(messageEditText);
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
      emojiButton.setImageResource(R.drawable.input_smile);
    }
    if (emojiView != null) {
      emojiView.setVisibility(GONE);
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
