public void setLoadingMoreEnabled(boolean loadingMoreEnabled){
  this.loadingMoreEnabled=loadingMoreEnabled;
  if (!loadingMoreEnabled) {
    if (mFootViews != null) {
      mFootViews.remove(0);
    }
  }
 else {
    if (mFootViews != null) {
      LoadingMoreFooter footView=new LoadingMoreFooter(getContext());
      addFootView(footView,false);
    }
  }
}
