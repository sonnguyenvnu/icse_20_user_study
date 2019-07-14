/** 
 * Notify the  {@link SectionTree} that it needs to synchronously perform a state update.
 * @param stateUpdate state update to perform
 */
public void updateStateSync(StateContainer.StateUpdate stateUpdate,String attribution){
  final Section section=mScope.get();
  final SectionTree sectionTree=mSectionTree;
  if (sectionTree == null || section == null) {
    return;
  }
  if (SectionsDebug.ENABLED) {
    Log.d(SectionsDebug.TAG,"(" + mSectionTree.hashCode() + ") updateState from " + stateUpdate.getClass().getName());
  }
  sectionTree.updateState(section.getGlobalKey(),stateUpdate,attribution);
}
