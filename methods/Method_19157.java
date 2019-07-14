/** 
 * Sent the RecyclerCollection a request to refresh it's backing data. If showSpinner is true, then refresh spinner is shown.
 * @param showSpinner
 */
public void requestRefresh(boolean showSpinner){
  if (mSectionTree != null && mSectionTree.get() != null) {
    if (showSpinner) {
      showRefreshing();
    }
    mSectionTree.get().refresh();
  }
}
