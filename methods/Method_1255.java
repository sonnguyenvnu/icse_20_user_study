@Override public void onSubmit(String id,Object callerContext){
  final long now=mClock.now();
  mImagePerfState.setControllerSubmitTimeMs(now);
  mImagePerfState.setControllerId(id);
  mImagePerfState.setCallerContext(callerContext);
  mImagePerfMonitor.notifyStatusUpdated(mImagePerfState,ImageLoadStatus.REQUESTED);
  reportViewVisible(now);
}
