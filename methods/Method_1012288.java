/** 
 * ??
 */
@Override public void show(){
  setVisibility(VISIBLE);
  if (mLoadingView != null) {
    mLoadingView.start();
  }
}
