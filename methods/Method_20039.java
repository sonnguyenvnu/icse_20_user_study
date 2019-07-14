/** 
 * Selects the most suitable preview and picture size, given the desired width and height. <p>Even though we only need to find the preview size, it's necessary to find both the preview size and the picture size of the camera together, because these need to have the same aspect ratio. On some hardware, if you would only set the preview size, you will get a distorted image.
 * @param camera the camera to select a preview size from
 * @param desiredWidth the desired width of the camera preview frames
 * @param desiredHeight the desired height of the camera preview frames
 * @return the selected preview and picture size pair
 */
private static SizePair selectSizePair(Camera camera,int desiredWidth,int desiredHeight){
  List<SizePair> validPreviewSizes=generateValidPreviewSizeList(camera);
  SizePair selectedPair=null;
  int minDiff=Integer.MAX_VALUE;
  for (  SizePair sizePair : validPreviewSizes) {
    Size size=sizePair.previewSize();
    int diff=Math.abs(size.getWidth() - desiredWidth) + Math.abs(size.getHeight() - desiredHeight);
    if (diff < minDiff) {
      selectedPair=sizePair;
      minDiff=diff;
    }
  }
  return selectedPair;
}
