private void switchToCurrentSelectedMode(boolean animated){
  for (int a=0; a < viewPages.length; a++) {
    viewPages[a].listView.stopScroll();
  }
  int a=animated ? 1 : 0;
  RecyclerView.Adapter currentAdapter=viewPages[a].listView.getAdapter();
  viewPages[a].listView.setPinnedHeaderShadowDrawable(null);
  if (viewPages[a].selectedType == 0) {
    if (currentAdapter != mobileAdapter) {
      viewPages[a].listView.setAdapter(mobileAdapter);
    }
  }
 else   if (viewPages[a].selectedType == 1) {
    if (currentAdapter != wifiAdapter) {
      viewPages[a].listView.setAdapter(wifiAdapter);
    }
  }
 else   if (viewPages[a].selectedType == 2) {
    if (currentAdapter != roamingAdapter) {
      viewPages[a].listView.setAdapter(roamingAdapter);
    }
  }
  viewPages[a].listView.setVisibility(View.VISIBLE);
  if (actionBar.getTranslationY() != 0) {
    viewPages[a].layoutManager.scrollToPositionWithOffset(0,(int)actionBar.getTranslationY());
  }
}
