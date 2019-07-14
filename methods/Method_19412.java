@Override public void onScrolled(RecyclerView recyclerView,int dx,int dy){
  final int firstVisiblePosition=mHasStickyHeader.findFirstVisibleItemPosition();
  if (firstVisiblePosition == RecyclerView.NO_POSITION) {
    return;
  }
  final int stickyHeaderPosition=findStickyHeaderPosition(firstVisiblePosition);
  final ComponentTree firstVisibleItemComponentTree=mHasStickyHeader.getComponentForStickyHeaderAt(firstVisiblePosition);
  if (lastTranslatedView != null && firstVisibleItemComponentTree != null && lastTranslatedView != firstVisibleItemComponentTree.getLithoView()) {
    lastTranslatedView.setTranslationY(0);
    lastTranslatedView=null;
  }
  if (stickyHeaderPosition == RecyclerView.NO_POSITION || firstVisibleItemComponentTree == null) {
    mSectionsRecyclerView.hideStickyHeader();
    previousStickyHeaderPosition=RecyclerView.NO_POSITION;
    return;
  }
  if (firstVisiblePosition == stickyHeaderPosition) {
    final LithoView firstVisibleView=firstVisibleItemComponentTree.getLithoView();
    if (firstVisibleView == null) {
      final ComponentsLogger logger=firstVisibleItemComponentTree.getContext().getLogger();
      if (logger != null) {
        logger.emitMessage(ComponentsLogger.LogLevel.ERROR,"First visible sticky header item is null" + ", RV.hasPendingAdapterUpdates: " + mSectionsRecyclerView.getRecyclerView().hasPendingAdapterUpdates() + ", first visible component: " + firstVisibleItemComponentTree.getSimpleName() + ", hasMounted: " + firstVisibleItemComponentTree.hasMounted() + ", isReleased: " + firstVisibleItemComponentTree.isReleased());
      }
    }
 else {
      if (!mHasStickyHeader.isValidPosition(stickyHeaderPosition + 1) || !mHasStickyHeader.isSticky(stickyHeaderPosition + 1)) {
        firstVisibleView.setTranslationY(-firstVisibleView.getTop());
      }
    }
    lastTranslatedView=firstVisibleView;
    mSectionsRecyclerView.hideStickyHeader();
    previousStickyHeaderPosition=RecyclerView.NO_POSITION;
  }
 else {
    if (mSectionsRecyclerView.isStickyHeaderHidden() || stickyHeaderPosition != previousStickyHeaderPosition) {
      initStickyHeader(stickyHeaderPosition);
      mSectionsRecyclerView.showStickyHeader();
    }
    final int lastVisiblePosition=mHasStickyHeader.findLastVisibleItemPosition();
    int translationY=0;
    for (int i=firstVisiblePosition; i <= lastVisiblePosition; i++) {
      if (mHasStickyHeader.isSticky(i)) {
        final View nextStickyHeader=mLayoutManager.findViewByPosition(i);
        final int offsetBetweenStickyHeaders=nextStickyHeader.getTop() - mSectionsRecyclerView.getStickyHeader().getBottom() + mSectionsRecyclerView.getPaddingTop();
        translationY=Math.min(offsetBetweenStickyHeaders,0);
        break;
      }
    }
    mSectionsRecyclerView.setStickyHeaderVerticalOffset(translationY);
    previousStickyHeaderPosition=stickyHeaderPosition;
  }
}
