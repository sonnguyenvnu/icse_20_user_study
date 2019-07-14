/** 
 * Checks to make sure the main thread layout state is valid (and throws if it's both invalid and there's no layout requested to make it valid).
 * @return whether the main thread layout state is ok to use
 */
private boolean checkMainThreadLayoutStateForIncrementalMount(){
  if (mComponentTree.getMainThreadLayoutState() != null) {
    return true;
  }
  if (!isLayoutRequested()) {
    throw new RuntimeException("Trying to incrementally mount a component with a null main thread LayoutState on a " + "LithoView that hasn't requested layout!");
  }
  return false;
}
