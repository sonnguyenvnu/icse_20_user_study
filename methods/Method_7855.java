private void setCurrentHeaderHeight(int newHeight){
  int maxHeight=AndroidUtilities.dp(56);
  int minHeight=Math.max(AndroidUtilities.statusBarHeight,AndroidUtilities.dp(24));
  if (newHeight < minHeight) {
    newHeight=minHeight;
  }
 else   if (newHeight > maxHeight) {
    newHeight=maxHeight;
  }
  float heightDiff=maxHeight - minHeight;
  currentHeaderHeight=newHeight;
  float scale=0.8f + (currentHeaderHeight - minHeight) / heightDiff * 0.2f;
  float scale2=0.5f + (currentHeaderHeight - minHeight) / heightDiff * 0.5f;
  backButton.setScaleX(scale);
  backButton.setScaleY(scale);
  backButton.setTranslationY((maxHeight - currentHeaderHeight) / 2);
  shareContainer.setScaleX(scale);
  shareContainer.setScaleY(scale);
  settingsButton.setScaleX(scale);
  settingsButton.setScaleY(scale);
  titleTextView.setScaleX(scale);
  titleTextView.setScaleY(scale);
  lineProgressView.setScaleY(scale2);
  shareContainer.setTranslationY((maxHeight - currentHeaderHeight) / 2);
  settingsButton.setTranslationY((maxHeight - currentHeaderHeight) / 2);
  titleTextView.setTranslationY((maxHeight - currentHeaderHeight) / 2);
  headerView.setTranslationY(currentHeaderHeight - maxHeight);
  for (int i=0; i < listView.length; i++) {
    listView[i].setTopGlowOffset(currentHeaderHeight);
  }
}
