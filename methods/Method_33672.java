/** 
 * ????
 */
private void swipeRefresh(){
  bindingView.srlWan.postDelayed(() -> {
    viewModel.setPage(0);
    bindingView.xrvWan.reset();
    getWanAndroidBanner();
  }
,350);
}
