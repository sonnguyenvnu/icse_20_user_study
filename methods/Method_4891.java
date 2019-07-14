private boolean shouldSkipOutputBuffer(long presentationTimeUs){
  int size=decodeOnlyPresentationTimestamps.size();
  for (int i=0; i < size; i++) {
    if (decodeOnlyPresentationTimestamps.get(i) == presentationTimeUs) {
      decodeOnlyPresentationTimestamps.remove(i);
      return true;
    }
  }
  return false;
}
