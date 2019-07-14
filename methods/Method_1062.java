private void releaseFetch(){
  boolean wasRequestSubmitted=mIsRequestSubmitted;
  mIsRequestSubmitted=false;
  mHasFetchFailed=false;
  if (mDataSource != null) {
    mDataSource.close();
    mDataSource=null;
  }
  if (mDrawable != null) {
    releaseDrawable(mDrawable);
  }
  if (mContentDescription != null) {
    mContentDescription=null;
  }
  mDrawable=null;
  if (mFetchedImage != null) {
    logMessageAndImage("release",mFetchedImage);
    releaseImage(mFetchedImage);
    mFetchedImage=null;
  }
  if (wasRequestSubmitted) {
    getControllerListener().onRelease(mId);
  }
}
