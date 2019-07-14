@Override public void onImageLoaded(String controllerId,@ImageOrigin int imageOrigin,boolean successful,String ultimateProducerName){
  mImagePerfState.setImageOrigin(imageOrigin);
  mImagePerfState.setUltimateProducerName(ultimateProducerName);
  mImagePerfMonitor.notifyStatusUpdated(mImagePerfState,ImageLoadStatus.ORIGIN_AVAILABLE);
}
