@Override public void onIntermediateImageSet(String id,@Nullable ImageInfo imageInfo){
  final long now=mClock.now();
  mImagePerfState.setControllerIntermediateImageSetTimeMs(now);
  mImagePerfState.setControllerId(id);
  mImagePerfState.setImageInfo(imageInfo);
  mImagePerfMonitor.notifyStatusUpdated(mImagePerfState,ImageLoadStatus.INTERMEDIATE_AVAILABLE);
}
