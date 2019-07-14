@Override public void onRequestFailure(ImageRequest request,String requestId,Throwable throwable,boolean isPrefetch){
  mImagePerfState.setImageRequestEndTimeMs(mClock.now());
  mImagePerfState.setImageRequest(request);
  mImagePerfState.setRequestId(requestId);
  mImagePerfState.setPrefetch(isPrefetch);
}
