private static List<Integer> getViewportFilteredTrackIndices(TrackGroup group,int viewportWidth,int viewportHeight,boolean orientationMayChange){
  ArrayList<Integer> selectedTrackIndices=new ArrayList<>(group.length);
  for (int i=0; i < group.length; i++) {
    selectedTrackIndices.add(i);
  }
  if (viewportWidth == Integer.MAX_VALUE || viewportHeight == Integer.MAX_VALUE) {
    return selectedTrackIndices;
  }
  int maxVideoPixelsToRetain=Integer.MAX_VALUE;
  for (int i=0; i < group.length; i++) {
    Format format=group.getFormat(i);
    if (format.width > 0 && format.height > 0) {
      Point maxVideoSizeInViewport=getMaxVideoSizeInViewport(orientationMayChange,viewportWidth,viewportHeight,format.width,format.height);
      int videoPixels=format.width * format.height;
      if (format.width >= (int)(maxVideoSizeInViewport.x * FRACTION_TO_CONSIDER_FULLSCREEN) && format.height >= (int)(maxVideoSizeInViewport.y * FRACTION_TO_CONSIDER_FULLSCREEN) && videoPixels < maxVideoPixelsToRetain) {
        maxVideoPixelsToRetain=videoPixels;
      }
    }
  }
  if (maxVideoPixelsToRetain != Integer.MAX_VALUE) {
    for (int i=selectedTrackIndices.size() - 1; i >= 0; i--) {
      Format format=group.getFormat(selectedTrackIndices.get(i));
      int pixelCount=format.getPixelCount();
      if (pixelCount == Format.NO_VALUE || pixelCount > maxVideoPixelsToRetain) {
        selectedTrackIndices.remove(i);
      }
    }
  }
  return selectedTrackIndices;
}
