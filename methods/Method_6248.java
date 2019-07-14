private int roundOrientation(int orientation,int orientationHistory){
  boolean changeOrientation;
  if (orientationHistory == OrientationEventListener.ORIENTATION_UNKNOWN) {
    changeOrientation=true;
  }
 else {
    int dist=Math.abs(orientation - orientationHistory);
    dist=Math.min(dist,360 - dist);
    changeOrientation=(dist >= 45 + ORIENTATION_HYSTERESIS);
  }
  if (changeOrientation) {
    return ((orientation + 45) / 90 * 90) % 360;
  }
  return orientationHistory;
}
