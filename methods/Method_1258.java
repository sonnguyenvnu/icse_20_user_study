@Override public void onRelease(String id){
  super.onRelease(id);
  final long now=mClock.now();
  int lastImageLoadStatus=mImagePerfState.getImageLoadStatus();
  if (lastImageLoadStatus != ImageLoadStatus.SUCCESS && lastImageLoadStatus != ImageLoadStatus.ERROR) {
    mImagePerfState.setControllerCancelTimeMs(now);
    mImagePerfState.setControllerId(id);
    mImagePerfMonitor.notifyStatusUpdated(mImagePerfState,ImageLoadStatus.CANCELED);
  }
  reportViewInvisible(now);
}
