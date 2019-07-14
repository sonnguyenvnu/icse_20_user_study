/** 
 * Gets the Y coordinate of the pivot point 
 */
public float getPivotY(){
  return calcAverage(mDetector.getStartY(),mDetector.getPointerCount());
}
