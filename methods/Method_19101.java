/** 
 * Creates the new tree, transfers state/services from the current tree and applies all the state updates that have been enqueued since the last tree calculation.
 */
private static void createNewTreeAndApplyStateUpdates(SectionContext context,Section currentRoot,Section nextRoot,Map<String,List<StateContainer.StateUpdate>> pendingStateUpdates,SectionsDebugLogger sectionsDebugLogger,String sectionTreeTag){
  if (nextRoot == null) {
    throw new IllegalStateException("Can't generate a subtree with a null root");
  }
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("createChildren:" + nextRoot.getSimpleName());
  }
  try {
    nextRoot.setScopedContext(SectionContext.withScope(context,nextRoot));
    if (currentRoot != null) {
      nextRoot.setCount(currentRoot.getCount());
    }
    final boolean shouldTransferState=currentRoot != null && currentRoot.getClass().equals(nextRoot.getClass());
    if (currentRoot == null || !shouldTransferState) {
      nextRoot.createInitialState(nextRoot.getScopedContext());
      nextRoot.createService(nextRoot.getScopedContext());
    }
 else {
      if (currentRoot.getService(currentRoot) == null) {
        nextRoot.createService(nextRoot.getScopedContext());
        if (nextRoot.getService(nextRoot) != null) {
          final String errorMessage="We were about to transfer a null service from " + currentRoot + " to " + nextRoot + " while the later created a non-null service";
          final ComponentsLogger logger=context.getLogger();
          if (logger != null) {
            logger.emitMessage(ERROR,errorMessage);
          }
 else {
            Log.e(SectionsDebug.TAG,errorMessage);
          }
        }
      }
 else {
        nextRoot.transferService(nextRoot.getScopedContext(),currentRoot,nextRoot);
      }
      nextRoot.transferState(currentRoot.getStateContainer(),nextRoot.getStateContainer());
    }
    final List<StateContainer.StateUpdate> stateUpdates=pendingStateUpdates.get(nextRoot.getGlobalKey());
    if (stateUpdates != null) {
      final StateContainer stateContainer=nextRoot.getStateContainer();
      for (int i=0, size=stateUpdates.size(); i < size; i++) {
        stateContainer.applyStateUpdate(stateUpdates.get(i));
      }
      if (nextRoot.shouldComponentUpdate(currentRoot,nextRoot)) {
        nextRoot.invalidate();
      }
    }
    if (!nextRoot.isDiffSectionSpec()) {
      final Map<String,Pair<Section,Integer>> currentComponentChildren=currentRoot == null || currentRoot.isDiffSectionSpec() ? null : Section.acquireChildrenMap(currentRoot);
      final TreeProps parentTreeProps=context.getTreeProps();
      nextRoot.populateTreeProps(parentTreeProps);
      context.setTreeProps(nextRoot.getTreePropsForChildren(context,parentTreeProps));
      final ComponentsLogger logger=context.getLogger();
      final PerfEvent logEvent=SectionsLogEventUtils.getSectionsPerformanceEvent(context,EVENT_SECTIONS_ON_CREATE_CHILDREN,null,nextRoot);
      nextRoot.setChildren(nextRoot.createChildren(nextRoot.getScopedContext()));
      if (logger != null && logEvent != null) {
        logger.logPerfEvent(logEvent);
      }
      final List<Section> nextRootChildren=nextRoot.getChildren();
      for (int i=0, size=nextRootChildren.size(); i < size; i++) {
        final Section child=nextRootChildren.get(i);
        child.setParent(nextRoot);
        final String childKey=child.getKey();
        if (TextUtils.isEmpty(childKey)) {
          final String errorMessage="Your Section " + child.getClass().getSimpleName() + " has an empty key. Please specify a key.";
          throw new IllegalStateException(errorMessage);
        }
        final String globalKey=nextRoot.getGlobalKey() + childKey;
        child.generateKeyAndSet(nextRoot.getScopedContext(),globalKey);
        child.setScopedContext(SectionContext.withScope(context,child));
        final Pair<Section,Integer> valueAndIndex=currentComponentChildren == null ? null : currentComponentChildren.get(child.getGlobalKey());
        final Section currentChild=valueAndIndex != null ? valueAndIndex.first : null;
        createNewTreeAndApplyStateUpdates(context,currentChild,child,pendingStateUpdates,sectionsDebugLogger,sectionTreeTag);
      }
      final TreeProps contextTreeProps=context.getTreeProps();
      if (contextTreeProps != parentTreeProps) {
        context.setTreeProps(parentTreeProps);
      }
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
