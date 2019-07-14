public void notifyListenersOfVisibilityStateUpdate(ImagePerfState state,@VisibilityState int visibilityState){
  if (!mEnabled || mImagePerfDataListeners == null || mImagePerfDataListeners.isEmpty()) {
    return;
  }
  ImagePerfData data=state.snapshot();
  for (  ImagePerfDataListener listener : mImagePerfDataListeners) {
    listener.onImageVisibilityUpdated(data,visibilityState);
  }
}
