private void setScrollY(float value){
  actionBar.setTranslationY(value);
  if (fragmentContextView != null) {
    fragmentContextView.setTranslationY(additionalPadding + value);
  }
  for (int a=0; a < mediaPages.length; a++) {
    mediaPages[a].listView.setPinnedSectionOffsetY((int)value);
  }
  fragmentView.invalidate();
}
