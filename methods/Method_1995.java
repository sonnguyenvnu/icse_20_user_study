/** 
 * Gets the scale 
 */
public float getScale(){
  if (mDetector.getPointerCount() < 2) {
    return 1;
  }
 else {
    float startDeltaX=mDetector.getStartX()[1] - mDetector.getStartX()[0];
    float startDeltaY=mDetector.getStartY()[1] - mDetector.getStartY()[0];
    float currentDeltaX=mDetector.getCurrentX()[1] - mDetector.getCurrentX()[0];
    float currentDeltaY=mDetector.getCurrentY()[1] - mDetector.getCurrentY()[0];
    float startDist=(float)Math.hypot(startDeltaX,startDeltaY);
    float currentDist=(float)Math.hypot(currentDeltaX,currentDeltaY);
    return currentDist / startDist;
  }
}
