private void ensureResourcesTarget(){
  if (mBroadcastResource != null) {
    mBroadcastResource.setTarget(this);
  }
  if (mCommentListResource != null) {
    mCommentListResource.setTarget(this);
  }
}
