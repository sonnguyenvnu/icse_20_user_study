private int findFirstProducerForSize(int startIndex,ResizeOptions resizeOptions){
  for (int i=startIndex; i < mThumbnailProducers.length; i++) {
    if (mThumbnailProducers[i].canProvideImageForSize(resizeOptions)) {
      return i;
    }
  }
  return -1;
}
