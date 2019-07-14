private void checkScrollForLoad(boolean scroll){
  if (chatLayoutManager == null || paused) {
    return;
  }
  int firstVisibleItem=chatLayoutManager.findFirstVisibleItemPosition();
  int visibleItemCount=firstVisibleItem == RecyclerView.NO_POSITION ? 0 : Math.abs(chatLayoutManager.findLastVisibleItemPosition() - firstVisibleItem) + 1;
  if (visibleItemCount > 0) {
    int totalItemCount=chatAdapter.getItemCount();
    int checkLoadCount;
    if (scroll) {
      checkLoadCount=25;
    }
 else {
      checkLoadCount=5;
    }
    if (firstVisibleItem <= checkLoadCount && !loading && !endReached) {
      loadMessages(false);
    }
  }
}
