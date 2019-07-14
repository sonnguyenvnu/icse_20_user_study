/** 
 * Gets the Y component of the translation 
 */
public float getTranslationY(){
  return calcAverage(mDetector.getCurrentY(),mDetector.getPointerCount()) - calcAverage(mDetector.getStartY(),mDetector.getPointerCount());
}
