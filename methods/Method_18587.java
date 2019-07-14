/** 
 * Called when the MountItem this Delegate is referred to is moved to another position to also update the indexes of the TouchExpansionDelegate.
 */
void moveTouchExpansionIndexes(int oldIndex,int newIndex){
  if (mDelegates.get(newIndex) != null) {
    ensureScrapDelegates();
    ComponentHostUtils.scrapItemAt(newIndex,mDelegates,mScrapDelegates);
  }
  ComponentHostUtils.moveItem(oldIndex,newIndex,mDelegates,mScrapDelegates);
  releaseScrapDelegatesIfNeeded();
}
