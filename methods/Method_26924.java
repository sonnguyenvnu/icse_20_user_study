/** 
 * @hide 
 */
@Override public void forceVisibility(int visibility,boolean isStartValue){
  int numTransitions=mTransitions.size();
  for (int i=0; i < numTransitions; i++) {
    mTransitions.get(i).forceVisibility(visibility,isStartValue);
  }
}
