void clearLithoView(){
  assertMainThread();
  if (mIsAttached) {
    throw new IllegalStateException("Clearing the LithoView while the ComponentTree is attached");
  }
  mLithoView=null;
}
