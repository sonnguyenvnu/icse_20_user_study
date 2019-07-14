/** 
 * Selects the most suitable preview frames per second range, given the desired frames per second.
 * @param camera the camera to select a frames per second range from
 * @param desiredPreviewFps the desired frames per second for the camera preview frames
 * @return the selected preview frames per second range
 */
@SuppressLint("InlinedApi") private static int[] selectPreviewFpsRange(Camera camera,float desiredPreviewFps){
  int desiredPreviewFpsScaled=(int)(desiredPreviewFps * 1000.0f);
  int[] selectedFpsRange=null;
  int minDiff=Integer.MAX_VALUE;
  List<int[]> previewFpsRangeList=camera.getParameters().getSupportedPreviewFpsRange();
  for (  int[] range : previewFpsRangeList) {
    int deltaMin=desiredPreviewFpsScaled - range[Camera.Parameters.PREVIEW_FPS_MIN_INDEX];
    int deltaMax=desiredPreviewFpsScaled - range[Camera.Parameters.PREVIEW_FPS_MAX_INDEX];
    int diff=Math.abs(deltaMin) + Math.abs(deltaMax);
    if (diff < minDiff) {
      selectedFpsRange=range;
      minDiff=diff;
    }
  }
  return selectedFpsRange;
}
