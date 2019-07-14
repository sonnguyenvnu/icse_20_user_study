/** 
 * Calculate the  {@link ChangeSet} for this ChangeSetState. The returned ChangeSet will be theresult of merging all the changeSets for all the leafs of the tree. As a result of calculating the  {@link ChangeSet} all the nodes in the new tree will be populated with the number of itemsin their subtree.
 */
static ChangeSetState generateChangeSet(SectionContext sectionContext,@Nullable Section currentRoot,Section newRoot,SectionsDebugLogger sectionsDebugLogger,String sectionTreeTag,String currentPrefix,String nextPrefix,boolean enableStats){
  ChangeSetState changeSetState=acquireChangeSetState();
  changeSetState.mCurrentRoot=currentRoot;
  changeSetState.mNewRoot=newRoot;
  final ComponentsLogger logger=sectionContext.getLogger();
  final PerfEvent logEvent=SectionsLogEventUtils.getSectionsPerformanceEvent(sectionContext,EVENT_SECTIONS_GENERATE_CHANGESET,currentRoot,newRoot);
  if (currentRoot != null && newRoot != null && !currentRoot.getSimpleName().equals(newRoot.getSimpleName())) {
    ChangeSet remove=generateChangeSetRecursive(sectionContext,currentRoot,null,changeSetState.mRemovedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,nextPrefix,Thread.currentThread().getName(),enableStats);
    ChangeSet add=generateChangeSetRecursive(sectionContext,null,newRoot,changeSetState.mRemovedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,nextPrefix,Thread.currentThread().getName(),enableStats);
    changeSetState.mChangeSet=ChangeSet.merge(remove,add);
  }
 else {
    changeSetState.mChangeSet=generateChangeSetRecursive(sectionContext,currentRoot,newRoot,changeSetState.mRemovedComponents,sectionsDebugLogger,sectionTreeTag,currentPrefix,nextPrefix,Thread.currentThread().getName(),enableStats);
  }
  if (logger != null && logEvent != null) {
    logEvent.markerAnnotate(PARAM_CURRENT_ROOT_COUNT,currentRoot == null ? -1 : currentRoot.getCount());
    logEvent.markerAnnotate(PARAM_CHANGESET_CHANGE_COUNT,changeSetState.mChangeSet.getChangeCount());
    logEvent.markerAnnotate(PARAM_CHANGESET_FINAL_COUNT,changeSetState.mChangeSet.getCount());
    final ChangeSet.ChangeSetStats changeSetStats=changeSetState.mChangeSet.getChangeSetStats();
    if (changeSetStats != null) {
      logEvent.markerAnnotate(PARAM_CHANGESET_EFFECTIVE_COUNT,changeSetStats.getEffectiveChangesCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_INSERT_SINGLE_COUNT,changeSetStats.getInsertSingleCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_INSERT_RANGE_COUNT,changeSetStats.getInsertRangeCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_DELETE_SINGLE_COUNT,changeSetStats.getDeleteSingleCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_DELETE_RANGE_COUNT,changeSetStats.getDeleteRangeCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_UPDATE_SINGLE_COUNT,changeSetStats.getUpdateSingleCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_UPDATE_RANGE_COUNT,changeSetStats.getUpdateRangeCount());
      logEvent.markerAnnotate(PARAM_CHANGESET_MOVE_COUNT,changeSetStats.getMoveCount());
    }
    logger.logPerfEvent(logEvent);
  }
  checkCount(currentRoot,newRoot,changeSetState);
  return changeSetState;
}
