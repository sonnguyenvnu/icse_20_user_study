/** 
 * Saves the state of this bucket, returning it in an opaque object. Call restoreState to undo any changes that were made since the state was saved. Calls to saveState may be nested.
 * @return opaque saved state, which may be passed to restoreState
 */
public Object saveState(){
  if (iSavedState == null) {
    iSavedState=new SavedState();
  }
  return iSavedState;
}
