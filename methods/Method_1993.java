/** 
 * Gets the X component of the translation 
 */
public float getTranslationX(){
  return calcAverage(mDetector.getCurrentX(),mDetector.getPointerCount()) - calcAverage(mDetector.getStartX(),mDetector.getPointerCount());
}
