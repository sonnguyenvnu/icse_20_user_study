/** 
 * Called from the ComponentTree when a new view want to use the same ComponentTree.
 */
void clearComponentTree(){
  assertMainThread();
  if (mIsAttached) {
    throw new IllegalStateException("Trying to clear the ComponentTree while attached.");
  }
  mComponentTree=null;
  mNullComponentCause="clear_CT";
}
