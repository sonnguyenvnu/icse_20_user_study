private void clearThumbnailAndProgressIndicator(){
  if (mThumbnailView != null) {
    removeView(mThumbnailView);
    mThumbnailView=null;
  }
  if (mProgressIndicatorView != null) {
    removeView(mProgressIndicatorView);
    mProgressIndicatorView=null;
  }
}
