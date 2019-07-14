/** 
 * Updates the currently visible item range. <p> Identifies the data items that have not been loaded yet and initiates loading them in the background. Should be called from the view's scroll listener (such as {@link RecyclerView.OnScrollListener#onScrolled}).
 */
public void onRangeChanged(){
  if (isRefreshPending()) {
    return;
  }
  updateRange();
  mAllowScrollHints=true;
}
