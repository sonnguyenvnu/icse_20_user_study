@Override public void onImageLoadStatusUpdated(ImagePerfData imagePerfData,@ImageLoadStatus int imageLoadStatus){
  Log.d(TAG,String.format((Locale)null,"status=%s, data=%s",ImagePerfUtils.toString(imageLoadStatus),imagePerfData.createDebugString()));
}
