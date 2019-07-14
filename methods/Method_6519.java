public void cancelForceLoadingForImageReceiver(final ImageReceiver imageReceiver){
  if (imageReceiver == null) {
    return;
  }
  final String key=imageReceiver.getImageKey();
  if (key == null) {
    return;
  }
  imageLoadQueue.postRunnable(() -> forceLoadingImages.remove(key));
}
