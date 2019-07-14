public void notifyStatusUpdated(ImagePerfState state,@ImageLoadStatus int imageLoadStatus){
  state.setImageLoadStatus(imageLoadStatus);
  if (!mEnabled || mImagePerfDataListeners == null || mImagePerfDataListeners.isEmpty()) {
    return;
  }
  if (imageLoadStatus == ImageLoadStatus.SUCCESS) {
    addViewportData();
  }
  ImagePerfData data=state.snapshot();
  for (  ImagePerfDataListener listener : mImagePerfDataListeners) {
    listener.onImageLoadStatusUpdated(data,imageLoadStatus);
  }
}
