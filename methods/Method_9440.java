private void needLayout(){
  FrameLayout.LayoutParams layoutParams;
  int newTop=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight();
  if (listView != null) {
    layoutParams=(FrameLayout.LayoutParams)listView.getLayoutParams();
    if (layoutParams.topMargin != newTop) {
      layoutParams.topMargin=newTop;
      listView.setLayoutParams(layoutParams);
      extraHeightView.setTranslationY(newTop);
    }
  }
  if (avatarImage != null) {
    float diff=extraHeight / (float)AndroidUtilities.dp(88);
    extraHeightView.setScaleY(diff);
    shadowView.setTranslationY(newTop + extraHeight);
    avatarImage.setScaleX((42 + 18 * diff) / 42.0f);
    avatarImage.setScaleY((42 + 18 * diff) / 42.0f);
    float avatarY=(actionBar.getOccupyStatusBar() ? AndroidUtilities.statusBarHeight : 0) + ActionBar.getCurrentActionBarHeight() / 2.0f * (1.0f + diff) - 21 * AndroidUtilities.density + 27 * AndroidUtilities.density * diff;
    avatarImage.setTranslationX(-AndroidUtilities.dp(47) * diff);
    avatarImage.setTranslationY((float)Math.ceil(avatarY));
    nameTextView.setTranslationX(-21 * AndroidUtilities.density * diff);
    nameTextView.setTranslationY((float)Math.floor(avatarY) - (float)Math.ceil(AndroidUtilities.density) + (float)Math.floor(7 * AndroidUtilities.density * diff));
    nameTextView.setScaleX(1.0f + 0.12f * diff);
    nameTextView.setScaleY(1.0f + 0.12f * diff);
  }
}
