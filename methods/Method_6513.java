public void cancelLoadingForImageReceiver(final ImageReceiver imageReceiver,final boolean cancelAll){
  if (imageReceiver == null) {
    return;
  }
  imageLoadQueue.postRunnable(() -> {
    for (int a=0; a < 3; a++) {
      int imageType;
      if (a > 0 && !cancelAll) {
        return;
      }
      if (a == 0) {
        imageType=ImageReceiver.TYPE_THUMB;
      }
 else       if (a == 1) {
        imageType=ImageReceiver.TYPE_IMAGE;
      }
 else {
        imageType=ImageReceiver.TYPE_MEDIA;
      }
      int TAG=imageReceiver.getTag(imageType);
      if (TAG != 0) {
        if (a == 0) {
          removeFromWaitingForThumb(TAG,imageReceiver);
        }
        CacheImage ei=imageLoadingByTag.get(TAG);
        if (ei != null) {
          ei.removeImageReceiver(imageReceiver);
        }
      }
    }
  }
);
}
