/** 
 * Update the root Section. This will create the new Section tree and generate a  {@link ChangeSet}to be applied to the UI. In response to this  {@link Target#applyNewChangeSet(int,String,Throwable)} will be invoked once the {@link ChangeSet} has been calculated. The generation ofthe ChangeSet will happen asynchronously in this SectionTree ChangeSetThread.
 * @param section The new root.
 */
public void setRootAsync(Section section){
  if (mReleased) {
    throw new IllegalStateException("Setting root on a released tree");
  }
synchronized (this) {
    if (mCurrentSection != null && mCurrentSection.getId() == section.getId()) {
      return;
    }
    if (mNextSection != null && mNextSection.getId() == section.getId()) {
      return;
    }
    mNextSection=copy(section,false);
  }
  mCalculateChangeSetRunnable.ensurePosted(ApplyNewChangeSet.SET_ROOT_ASYNC,null);
}
