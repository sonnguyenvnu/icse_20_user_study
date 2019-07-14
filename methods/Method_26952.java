/** 
 * @hide 
 */
@Override public void forceVisibility(int visibility,boolean isStartValue){
  if (isStartValue) {
    mForcedStartVisibility=visibility;
  }
 else {
    mForcedEndVisibility=visibility;
  }
}
