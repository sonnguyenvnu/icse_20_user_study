private void checkGifSearchFieldScroll(boolean isLayout){
  if (gifGridView != null && gifGridView.getAdapter() == gifSearchAdapter && !gifSearchAdapter.searchEndReached && gifSearchAdapter.reqId == 0 && !gifSearchAdapter.results.isEmpty()) {
    int position=gifLayoutManager.findLastVisibleItemPosition();
    if (position != RecyclerView.NO_POSITION && position > gifLayoutManager.getItemCount() - 5) {
      gifSearchAdapter.search(gifSearchAdapter.lastSearchImageString,gifSearchAdapter.nextSearchOffset,true);
    }
  }
  if (delegate != null && delegate.isSearchOpened()) {
    RecyclerView.ViewHolder holder=gifGridView.findViewHolderForAdapterPosition(0);
    if (holder == null) {
      gifSearchField.showShadow(true,!isLayout);
    }
 else {
      gifSearchField.showShadow(holder.itemView.getTop() < gifGridView.getPaddingTop(),!isLayout);
    }
    return;
  }
  if (gifSearchField == null || gifGridView == null) {
    return;
  }
  RecyclerView.ViewHolder holder=gifGridView.findViewHolderForAdapterPosition(0);
  if (holder != null) {
    gifSearchField.setTranslationY(holder.itemView.getTop());
  }
 else {
    gifSearchField.setTranslationY(-searchFieldHeight);
  }
  gifSearchField.showShadow(false,!isLayout);
}
