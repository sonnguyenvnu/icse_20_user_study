/** 
 * @return true if the view is measured and doesn't need remeasuring. 
 */
private synchronized boolean isMeasured(){
  return mIsMeasured.get() && !mRequiresRemeasure.get();
}
