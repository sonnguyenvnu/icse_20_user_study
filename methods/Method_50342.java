private static void checkZoomLevels(float minZoom,float midZoom,float maxZoom){
  if (minZoom >= midZoom) {
    throw new IllegalArgumentException("Minimum zoom has to be less than Medium zoom. Call setMinimumZoom() with a more appropriate value");
  }
 else   if (midZoom >= maxZoom) {
    throw new IllegalArgumentException("Medium zoom has to be less than Maximum zoom. Call setMaximumZoom() with a more appropriate value");
  }
}
