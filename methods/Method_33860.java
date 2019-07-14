private void loadMoreComplete(){
  isLoadingData=false;
  View footView=mFootViews.get(0);
  if (previousTotal <= getLayoutManager().getItemCount()) {
    if (footView instanceof LoadingMoreFooter) {
      ((LoadingMoreFooter)footView).setState(LoadingMoreFooter.STATE_COMPLETE);
    }
 else {
      footView.setVisibility(View.GONE);
    }
  }
 else {
    if (footView instanceof LoadingMoreFooter) {
      ((LoadingMoreFooter)footView).setState(LoadingMoreFooter.STATE_NOMORE);
    }
 else {
      footView.setVisibility(View.GONE);
    }
    isnomore=true;
  }
  previousTotal=getLayoutManager().getItemCount();
}
