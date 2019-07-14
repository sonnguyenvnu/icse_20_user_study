/** 
 * Gets the rotation in radians 
 */
public float getRotation(){
  if (mDetector.getPointerCount() < 2) {
    return 0;
  }
 else {
    float startDeltaX=mDetector.getStartX()[1] - mDetector.getStartX()[0];
    float startDeltaY=mDetector.getStartY()[1] - mDetector.getStartY()[0];
    float currentDeltaX=mDetector.getCurrentX()[1] - mDetector.getCurrentX()[0];
    float currentDeltaY=mDetector.getCurrentY()[1] - mDetector.getCurrentY()[0];
    float startAngle=(float)Math.atan2(startDeltaY,startDeltaX);
    float currentAngle=(float)Math.atan2(currentDeltaY,currentDeltaX);
    return currentAngle - startAngle;
  }
}
