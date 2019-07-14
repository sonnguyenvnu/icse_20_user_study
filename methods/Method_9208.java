private void setScrollY(float value){
  actionBar.setTranslationY(value);
  for (int a=0; a < viewPages.length; a++) {
    viewPages[a].listView.setPinnedSectionOffsetY((int)value);
  }
  fragmentView.invalidate();
}
