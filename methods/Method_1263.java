@Override public void onRequestSuccess(ImageRequest request,String requestId,boolean isPrefetch){
  mImagePerfState.setImageRequestEndTimeMs(mClock.now());
  mImagePerfState.setImageRequest(request);
  mImagePerfState.setRequestId(requestId);
  mImagePerfState.setPrefetch(isPrefetch);
}
