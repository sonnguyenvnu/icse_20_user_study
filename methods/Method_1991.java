/** 
 * Gets the X coordinate of the pivot point 
 */
public float getPivotX(){
  return calcAverage(mDetector.getStartX(),mDetector.getPointerCount());
}
