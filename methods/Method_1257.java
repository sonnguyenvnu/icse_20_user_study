@Override public void onFinalImageSet(String id,@Nullable ImageInfo imageInfo,@Nullable Animatable animatable){
  final long now=mClock.now();
  mImagePerfState.setControllerFinalImageSetTimeMs(now);
  mImagePerfState.setImageRequestEndTimeMs(now);
  mImagePerfState.setControllerId(id);
  mImagePerfState.setImageInfo(imageInfo);
  mImagePerfMonitor.notifyStatusUpdated(mImagePerfState,ImageLoadStatus.SUCCESS);
}
