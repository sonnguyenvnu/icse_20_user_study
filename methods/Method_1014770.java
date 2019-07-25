/** 
 * ?????????????
 * @param touchCoordinateInCameraReper
 * @param focusAreaSize
 * @return
 */
private static int clamp(int touchCoordinateInCameraReper,int focusAreaSize){
  int result;
  if (Math.abs(touchCoordinateInCameraReper) + focusAreaSize > 1000) {
    if (touchCoordinateInCameraReper > 0) {
      result=1000 - focusAreaSize;
    }
 else {
      result=-1000 + focusAreaSize;
    }
  }
 else {
    result=touchCoordinateInCameraReper - focusAreaSize / 2;
  }
  return result;
}
