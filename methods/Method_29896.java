@Override protected void onAttachScrollListener(){
  AppBarHost appBarHost=(AppBarHost)getParentFragment();
  mList.addOnScrollListener(new OnVerticalScrollWithPagingTouchSlopListener(getActivity()){
    @Override public void onScrolled(    int dy){
      if (!RecyclerViewUtils.hasFirstChildReachedTop(mList)) {
        onShow();
      }
    }
    @Override public void onScrolledUp(){
      onShow();
    }
    private void onShow(){
      appBarHost.showAppBar();
      mSendFab.show();
    }
    @Override public void onScrolledDown(){
      if (RecyclerViewUtils.hasFirstChildReachedTop(mList)) {
        appBarHost.hideAppBar();
        mSendFab.hide();
      }
    }
    @Override public void onScrolledToBottom(){
      mResource.load(true);
    }
  }
);
  appBarHost.setToolBarOnDoubleClickListener(view -> {
    mList.smoothScrollToPosition(0);
    return true;
  }
);
}
