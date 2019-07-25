/** 
 * ??
 */
@Override public void dismiss(){
  if (mLoadingView != null) {
    mLoadingView.stop();
  }
  setVisibility(GONE);
}
