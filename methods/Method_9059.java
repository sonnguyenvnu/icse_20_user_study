private void checkScroll(){
  int firstVisibleItem=stickersLayoutManager.findFirstVisibleItemPosition();
  if (firstVisibleItem == RecyclerView.NO_POSITION) {
    return;
  }
  checkStickersScroll(firstVisibleItem);
}
