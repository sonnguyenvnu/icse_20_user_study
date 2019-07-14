@Override public void onRequestStart(ImageRequest request,Object callerContext,String requestId,boolean isPrefetch){
  mImagePerfState.setImageRequestStartTimeMs(mClock.now());
  mImagePerfState.setImageRequest(request);
  mImagePerfState.setCallerContext(callerContext);
  mImagePerfState.setRequestId(requestId);
  mImagePerfState.setPrefetch(isPrefetch);
}
