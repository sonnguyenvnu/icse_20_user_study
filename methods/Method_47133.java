public void reloadListElements(boolean back,boolean results,boolean grid){
  if (isAdded()) {
    boolean isOtg=CURRENT_PATH.equals(OTGUtil.PREFIX_OTG + "/"), isOnTheCloud=CURRENT_PATH.equals(CloudHandler.CLOUD_PREFIX_GOOGLE_DRIVE + "/") || CURRENT_PATH.equals(CloudHandler.CLOUD_PREFIX_ONE_DRIVE + "/") || CURRENT_PATH.equals(CloudHandler.CLOUD_PREFIX_BOX + "/") || CURRENT_PATH.equals(CloudHandler.CLOUD_PREFIX_DROPBOX + "/");
    if (getBoolean(PREFERENCE_SHOW_GOBACK_BUTTON) && !CURRENT_PATH.equals("/") && (openMode == OpenMode.FILE || openMode == OpenMode.ROOT) && !isOtg && !isOnTheCloud && (LIST_ELEMENTS.size() == 0 || !LIST_ELEMENTS.get(0).size.equals(getString(R.string.goback)))) {
      LIST_ELEMENTS.add(0,getBackElement());
    }
    if (LIST_ELEMENTS.size() == 0 && !results) {
      nofilesview.setVisibility(View.VISIBLE);
      listView.setVisibility(View.GONE);
      mSwipeRefreshLayout.setEnabled(false);
    }
 else {
      mSwipeRefreshLayout.setEnabled(true);
      nofilesview.setVisibility(View.GONE);
      listView.setVisibility(View.VISIBLE);
    }
    if (grid && IS_LIST)     switchToGrid();
 else     if (!grid && !IS_LIST)     switchToList();
    if (adapter == null) {
      adapter=new RecyclerAdapter(getMainActivity(),ma,utilsProvider,sharedPref,listView,LIST_ELEMENTS,ma.getActivity());
    }
 else {
      adapter.setItems(listView,new ArrayList<>(LIST_ELEMENTS));
    }
    stopAnims=true;
    if (openMode != OpenMode.CUSTOM) {
      dataUtils.addHistoryFile(CURRENT_PATH);
    }
    listView.setAdapter(adapter);
    if (!addheader) {
      listView.removeItemDecoration(dividerItemDecoration);
      addheader=true;
    }
    if (addheader && IS_LIST) {
      dividerItemDecoration=new DividerItemDecoration(getActivity(),true,getBoolean(PREFERENCE_SHOW_DIVIDERS));
      listView.addItemDecoration(dividerItemDecoration);
      addheader=false;
    }
    if (!results) {
      this.results=false;
    }
    if (back && scrolls.containsKey(CURRENT_PATH)) {
      Bundle b=scrolls.get(CURRENT_PATH);
      int index=b.getInt("index"), top=b.getInt("top");
      if (IS_LIST) {
        mLayoutManager.scrollToPositionWithOffset(index,top);
      }
 else {
        mLayoutManagerGrid.scrollToPositionWithOffset(index,top);
      }
    }
    getMainActivity().updatePaths(no);
    listView.stopScroll();
    fastScroller.setRecyclerView(listView,IS_LIST ? 1 : columns);
    mToolbarContainer.addOnOffsetChangedListener((appBarLayout,verticalOffset) -> {
      fastScroller.updateHandlePosition(verticalOffset,112);
    }
);
    fastScroller.registerOnTouchListener(() -> {
      if (stopAnims && adapter != null) {
        stopAnimation();
        stopAnims=false;
      }
    }
);
    startFileObserver();
  }
 else {
    loadlist(home,true,OpenMode.FILE);
  }
}
