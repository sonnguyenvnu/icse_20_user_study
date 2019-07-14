@Override protected void onRefresh(){
  bindingView.srlWan.setRefreshing(true);
  if (!isLoadBanner) {
    getWanAndroidBanner();
  }
 else {
    bindingView.srlWan.postDelayed(this::getHomeList,500);
  }
}
