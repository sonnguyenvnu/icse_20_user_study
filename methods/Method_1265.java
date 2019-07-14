@Override public void onRequestCancellation(String requestId){
  mImagePerfState.setImageRequestEndTimeMs(mClock.now());
  mImagePerfState.setRequestId(requestId);
}
