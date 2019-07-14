@Override public void onImageVisibilityUpdated(ImagePerfData imagePerfData,@VisibilityState int visibility){
  Log.d(TAG,String.format((Locale)null,"visibility=%s, data=%s",ImagePerfUtils.toString(imagePerfData.getVisibilityState()),imagePerfData.createDebugString()));
}
