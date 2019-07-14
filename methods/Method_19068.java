/** 
 * Update the root Section. This will create the new Section tree and generate a  {@link ChangeSet}to be applied to the UI. In response to this  {@link Target#applyNewChangeSet(int)} ()} will beinvoked once the  {@link ChangeSet} has been calculated. The generation of the ChangeSet willhappen synchronously in the thread calling this method.
 * @param section The new root.
 */
public void setRoot(Section section){
  boolean isFirstSetRoot;
synchronized (this) {
    if (mReleased) {
      throw new IllegalStateException("Setting root on a released tree");
    }
    if (mCurrentSection != null && mCurrentSection.getId() == section.getId()) {
      return;
    }
    if (mNextSection != null && mNextSection.getId() == section.getId()) {
      return;
    }
    mNextSection=copy(section,false);
    isFirstSetRoot=mCurrentSection == null;
  }
  if (mAsyncPropUpdates && !isFirstSetRoot) {
    mCalculateChangeSetRunnable.ensurePosted(ApplyNewChangeSet.SET_ROOT_ASYNC,null);
  }
 else {
    applyNewChangeSet(ApplyNewChangeSet.SET_ROOT,null,null);
  }
}
