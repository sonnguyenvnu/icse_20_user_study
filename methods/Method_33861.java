public void noMoreLoading(){
  isLoadingData=false;
  final View footView=mFootViews.get(0);
  isnomore=true;
  if (footView instanceof LoadingMoreFooter) {
    ((LoadingMoreFooter)footView).setState(LoadingMoreFooter.STATE_NOMORE);
  }
 else {
    footView.setVisibility(View.GONE);
  }
  if (isOther) {
    footView.setVisibility(View.VISIBLE);
  }
}
