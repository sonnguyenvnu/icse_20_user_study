@Override public void onImageVisibilityUpdated(ImagePerfData imagePerfData,@VisibilityState int visibilityState){
  for (  ImagePerfDataListener listener : mListeners) {
    listener.onImageVisibilityUpdated(imagePerfData,visibilityState);
  }
}
