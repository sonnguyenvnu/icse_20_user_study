private void updateFieldRight(int attachVisible){
  if (messageEditText == null || editingMessageObject != null) {
    return;
  }
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)messageEditText.getLayoutParams();
  if (attachVisible == 1) {
    if (botButton != null && botButton.getVisibility() == VISIBLE || notifyButton != null && notifyButton.getVisibility() == VISIBLE) {
      layoutParams.rightMargin=AndroidUtilities.dp(98);
    }
 else {
      layoutParams.rightMargin=AndroidUtilities.dp(50);
    }
  }
 else   if (attachVisible == 2) {
    if (layoutParams.rightMargin != AndroidUtilities.dp(2)) {
      if (botButton != null && botButton.getVisibility() == VISIBLE || notifyButton != null && notifyButton.getVisibility() == VISIBLE) {
        layoutParams.rightMargin=AndroidUtilities.dp(98);
      }
 else {
        layoutParams.rightMargin=AndroidUtilities.dp(50);
      }
    }
  }
 else {
    layoutParams.rightMargin=AndroidUtilities.dp(2);
  }
  messageEditText.setLayoutParams(layoutParams);
}
