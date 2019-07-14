public void setAvatarPadding(int padding){
  FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)avatarImageView.getLayoutParams();
  layoutParams.leftMargin=AndroidUtilities.dp(LocaleController.isRTL ? 0 : 7 + padding);
  layoutParams.rightMargin=AndroidUtilities.dp(LocaleController.isRTL ? 7 + padding : 0);
  avatarImageView.setLayoutParams(layoutParams);
  layoutParams=(FrameLayout.LayoutParams)nameTextView.getLayoutParams();
  layoutParams.leftMargin=AndroidUtilities.dp(LocaleController.isRTL ? 28 + (checkBoxBig != null ? 18 : 0) : (64 + padding));
  layoutParams.rightMargin=AndroidUtilities.dp(LocaleController.isRTL ? (64 + padding) : 28 + (checkBoxBig != null ? 18 : 0));
  layoutParams=(FrameLayout.LayoutParams)statusTextView.getLayoutParams();
  layoutParams.leftMargin=AndroidUtilities.dp(LocaleController.isRTL ? 28 : (64 + padding));
  layoutParams.rightMargin=AndroidUtilities.dp(LocaleController.isRTL ? (64 + padding) : 28);
  if (checkBox != null) {
    layoutParams=(FrameLayout.LayoutParams)checkBox.getLayoutParams();
    layoutParams.leftMargin=AndroidUtilities.dp(LocaleController.isRTL ? 0 : 37 + padding);
    layoutParams.rightMargin=AndroidUtilities.dp(LocaleController.isRTL ? 37 + padding : 0);
  }
}
