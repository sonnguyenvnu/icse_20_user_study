private static ChangeSetState calculateNewChangeSet(SectionContext context,Section currentRoot,Section nextRoot,Map<String,List<StateContainer.StateUpdate>> pendingStateUpdates,SectionsDebugLogger sectionsDebugLogger,String sectionTreeTag,boolean enableStats){
  nextRoot.setGlobalKey(nextRoot.getKey());
  final ComponentsLogger logger=context.getLogger();
  final PerfEvent logEvent=SectionsLogEventUtils.getSectionsPerformanceEvent(context,EVENT_SECTIONS_CREATE_NEW_TREE,currentRoot,nextRoot);
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("createTree");
  }
  try {
    createNewTreeAndApplyStateUpdates(context,currentRoot,nextRoot,pendingStateUpdates,sectionsDebugLogger,sectionTreeTag);
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
  if (logger != null && logEvent != null) {
    logger.logPerfEvent(logEvent);
  }
  if (isTracing) {
    ComponentsSystrace.beginSection("ChangeSetState.generateChangeSet");
  }
  try {
    return ChangeSetState.generateChangeSet(context,currentRoot,nextRoot,sectionsDebugLogger,sectionTreeTag,"","",enableStats);
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
