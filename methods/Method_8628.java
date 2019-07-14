private void openSearch(SearchField searchField){
  if (searchAnimation != null) {
    searchAnimation.cancel();
    searchAnimation=null;
  }
  firstStickersAttach=false;
  firstGifAttach=false;
  firstEmojiAttach=false;
  for (int a=0; a < 3; a++) {
    RecyclerListView gridView;
    ScrollSlidingTabStrip tabStrip;
    SearchField currentField;
    GridLayoutManager layoutManager;
    if (a == 0) {
      currentField=emojiSearchField;
      gridView=emojiGridView;
      tabStrip=emojiTabs;
      layoutManager=emojiLayoutManager;
    }
 else     if (a == 1) {
      currentField=gifSearchField;
      gridView=gifGridView;
      tabStrip=null;
      layoutManager=gifLayoutManager;
    }
 else {
      currentField=stickersSearchField;
      gridView=stickersGridView;
      tabStrip=stickersTab;
      layoutManager=stickersLayoutManager;
    }
    if (currentField == null) {
      continue;
    }
    if (currentField != gifSearchField && searchField == currentField && delegate != null && delegate.isExpanded()) {
      searchAnimation=new AnimatorSet();
      if (tabStrip != null) {
        searchAnimation.playTogether(ObjectAnimator.ofFloat(tabStrip,View.TRANSLATION_Y,-AndroidUtilities.dp(48)),ObjectAnimator.ofFloat(gridView,View.TRANSLATION_Y,-AndroidUtilities.dp(48)),ObjectAnimator.ofFloat(currentField,View.TRANSLATION_Y,AndroidUtilities.dp(0)));
      }
 else {
        searchAnimation.playTogether(ObjectAnimator.ofFloat(gridView,View.TRANSLATION_Y,-AndroidUtilities.dp(48)),ObjectAnimator.ofFloat(currentField,View.TRANSLATION_Y,AndroidUtilities.dp(0)));
      }
      searchAnimation.setDuration(200);
      searchAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
      searchAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (animation.equals(searchAnimation)) {
            gridView.setTranslationY(0);
            if (gridView == stickersGridView) {
              gridView.setPadding(0,AndroidUtilities.dp(4),0,0);
            }
 else             if (gridView == emojiGridView) {
              gridView.setPadding(0,0,0,0);
            }
            searchAnimation=null;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (animation.equals(searchAnimation)) {
            searchAnimation=null;
          }
        }
      }
);
      searchAnimation.start();
    }
 else {
      currentField.setTranslationY(AndroidUtilities.dp(0));
      if (tabStrip != null) {
        tabStrip.setTranslationY(-AndroidUtilities.dp(48));
      }
      if (gridView == stickersGridView) {
        gridView.setPadding(0,AndroidUtilities.dp(4),0,0);
      }
 else       if (gridView == emojiGridView) {
        gridView.setPadding(0,0,0,0);
      }
      layoutManager.scrollToPositionWithOffset(0,0);
    }
  }
}
