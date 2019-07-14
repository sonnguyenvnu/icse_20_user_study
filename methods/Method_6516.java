public void replaceImageInCache(final String oldKey,final String newKey,final ImageLocation newLocation,boolean post){
  if (post) {
    AndroidUtilities.runOnUIThread(() -> replaceImageInCacheInternal(oldKey,newKey,newLocation));
  }
 else {
    replaceImageInCacheInternal(oldKey,newKey,newLocation);
  }
}
