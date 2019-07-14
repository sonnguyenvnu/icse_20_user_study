static LayoutState calculate(ComponentContext c,Component component,int componentTreeId,int widthSpec,int heightSpec,boolean shouldGenerateDiffTree,@Nullable LayoutState currentLayoutState,@CalculateLayoutSource int source,@Nullable String extraAttribution){
  final ComponentsLogger logger=c.getLogger();
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    if (extraAttribution != null) {
      ComponentsSystrace.beginSection("extra:" + extraAttribution);
    }
    ComponentsSystrace.beginSectionWithArgs(new StringBuilder("LayoutState.calculate_").append(component.getSimpleName()).append("_").append(sourceToString(source)).toString()).arg("treeId",componentTreeId).arg("rootId",component.getId()).arg("widthSpec",SizeSpec.toString(widthSpec)).arg("heightSpec",SizeSpec.toString(heightSpec)).flush();
  }
  final DiffNode diffTreeRoot=currentLayoutState != null ? currentLayoutState.mDiffTreeRoot : null;
  final LayoutState layoutState;
  try {
    final PerfEvent logLayoutState=logger != null ? LogTreePopulator.populatePerfEventFromLogger(c,logger,logger.newPerformanceEvent(c,EVENT_CALCULATE_LAYOUT_STATE)) : null;
    if (logLayoutState != null) {
      logLayoutState.markerAnnotate(PARAM_COMPONENT,component.getSimpleName());
      logLayoutState.markerAnnotate(PARAM_LAYOUT_STATE_SOURCE,sourceToString(source));
      logLayoutState.markerAnnotate(PARAM_TREE_DIFF_ENABLED,diffTreeRoot != null);
    }
    component.markLayoutStarted();
    layoutState=new LayoutState(c);
    layoutState.mShouldGenerateDiffTree=shouldGenerateDiffTree;
    layoutState.mComponentTreeId=componentTreeId;
    layoutState.mPreviousLayoutStateId=currentLayoutState != null ? currentLayoutState.mId : NO_PREVIOUS_LAYOUT_STATE_ID;
    layoutState.mAccessibilityManager=(AccessibilityManager)c.getAndroidContext().getSystemService(ACCESSIBILITY_SERVICE);
    layoutState.mAccessibilityEnabled=AccessibilityUtils.isAccessibilityEnabled(layoutState.mAccessibilityManager);
    layoutState.mComponent=component;
    layoutState.mWidthSpec=widthSpec;
    layoutState.mHeightSpec=heightSpec;
    layoutState.mRootComponentName=component.getSimpleName();
    final InternalNode layoutCreatedInWillRender=component.consumeLayoutCreatedInWillRender();
    final boolean isReconcilable=isReconcilable(c,component,currentLayoutState);
    final InternalNode root=layoutCreatedInWillRender == null ? createAndMeasureTreeForComponent(c,component,widthSpec,heightSpec,null,isReconcilable ? currentLayoutState.mLayoutRoot : null,diffTreeRoot,logLayoutState) : layoutCreatedInWillRender;
    layoutState.mLayoutRoot=root;
    layoutState.mRootTransitionId=getTransitionIdForNode(root);
    if (c.wasLayoutInterrupted()) {
      layoutState.mIsPartialLayoutState=true;
      return layoutState;
    }
    if (logLayoutState != null) {
      logLayoutState.markerPoint("start_collect_results");
    }
    setSizeAfterMeasureAndCollectResults(c,layoutState);
    if (logLayoutState != null) {
      logLayoutState.markerPoint("end_collect_results");
      logger.logPerfEvent(logLayoutState);
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
      if (extraAttribution != null) {
        ComponentsSystrace.endSection();
      }
    }
  }
  return layoutState;
}
