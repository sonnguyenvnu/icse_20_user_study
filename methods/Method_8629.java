public void closeSearch(boolean animated,long scrollToSet){
  if (searchAnimation != null) {
    searchAnimation.cancel();
    searchAnimation=null;
  }
  int currentItem=pager.getCurrentItem();
  if (currentItem == 2 && scrollToSet != -1) {
    TLRPC.TL_messages_stickerSet set=DataQuery.getInstance(currentAccount).getStickerSetById(scrollToSet);
    if (set != null) {
      int pos=stickersGridAdapter.getPositionForPack(set);
      if (pos >= 0) {
        stickersLayoutManager.scrollToPositionWithOffset(pos,AndroidUtilities.dp(48 + 12));
      }
    }
  }
  for (int a=0; a < 3; a++) {
    SearchField currentField;
    RecyclerListView gridView;
    GridLayoutManager layoutManager;
    ScrollSlidingTabStrip tabStrip;
    if (a == 0) {
      currentField=emojiSearchField;
      gridView=emojiGridView;
      layoutManager=emojiLayoutManager;
      tabStrip=emojiTabs;
    }
 else     if (a == 1) {
      currentField=gifSearchField;
      gridView=gifGridView;
      layoutManager=gifLayoutManager;
      tabStrip=null;
    }
 else {
      currentField=stickersSearchField;
      gridView=stickersGridView;
      layoutManager=stickersLayoutManager;
      tabStrip=stickersTab;
    }
    if (currentField == null) {
      continue;
    }
    currentField.searchEditText.setText("");
    if (a == currentItem && animated) {
      searchAnimation=new AnimatorSet();
      if (tabStrip != null) {
        searchAnimation.playTogether(ObjectAnimator.ofFloat(tabStrip,View.TRANSLATION_Y,0),ObjectAnimator.ofFloat(gridView,View.TRANSLATION_Y,AndroidUtilities.dp(48) - searchFieldHeight),ObjectAnimator.ofFloat(currentField,View.TRANSLATION_Y,AndroidUtilities.dp(48) - searchFieldHeight));
      }
 else {
        searchAnimation.playTogether(ObjectAnimator.ofFloat(gridView,View.TRANSLATION_Y,-searchFieldHeight),ObjectAnimator.ofFloat(currentField,View.TRANSLATION_Y,-searchFieldHeight));
      }
      searchAnimation.setDuration(200);
      searchAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
      searchAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (animation.equals(searchAnimation)) {
            int pos=layoutManager.findFirstVisibleItemPosition();
            int firstVisPos=layoutManager.findFirstVisibleItemPosition();
            int top=0;
            if (firstVisPos != RecyclerView.NO_POSITION) {
              View firstVisView=layoutManager.findViewByPosition(firstVisPos);
              top=(int)(firstVisView.getTop() + gridView.getTranslationY());
            }
            gridView.setTranslationY(0);
            if (gridView == stickersGridView) {
              gridView.setPadding(0,AndroidUtilities.dp(48 + 4),0,0);
            }
 else             if (gridView == emojiGridView) {
              gridView.setPadding(0,AndroidUtilities.dp(38),0,0);
            }
            if (gridView == gifGridView) {
              layoutManager.scrollToPositionWithOffset(1,0);
            }
 else {
              if (firstVisPos != RecyclerView.NO_POSITION) {
                layoutManager.scrollToPositionWithOffset(firstVisPos,top - gridView.getPaddingTop());
              }
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
      layoutManager.scrollToPositionWithOffset(1,0);
      currentField.setTranslationY(AndroidUtilities.dp(48) - searchFieldHeight);
      if (tabStrip != null) {
        tabStrip.setTranslationY(0);
      }
      if (gridView == stickersGridView) {
        gridView.setPadding(0,AndroidUtilities.dp(48 + 4),0,0);
      }
 else       if (gridView == emojiGridView) {
        gridView.setPadding(0,AndroidUtilities.dp(38),0,0);
      }
    }
  }
  if (!animated) {
    delegate.onSearchOpenClose(0);
  }
  showBottomTab(true,animated);
}
