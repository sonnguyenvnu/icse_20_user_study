static LayoutState resumeCalculate(ComponentContext c,@CalculateLayoutSource int source,@Nullable String extraAttribution,LayoutState layoutState){
  if (!layoutState.mIsPartialLayoutState) {
    throw new IllegalStateException("Can not resume a finished LayoutState calculation");
  }
  final Component component=layoutState.mComponent;
  final int componentTreeId=layoutState.mComponentTreeId;
  final int widthSpec=layoutState.mWidthSpec;
  final int heightSpec=layoutState.mHeightSpec;
  final ComponentsLogger logger=c.getLogger();
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    if (extraAttribution != null) {
      ComponentsSystrace.beginSection("extra:" + extraAttribution);
    }
    ComponentsSystrace.beginSectionWithArgs(new StringBuilder("LayoutState.resumeCalculate_").append(component.getSimpleName()).append("_").append(sourceToString(source)).toString()).arg("treeId",componentTreeId).arg("rootId",component.getId()).arg("widthSpec",SizeSpec.toString(widthSpec)).arg("heightSpec",SizeSpec.toString(heightSpec)).flush();
  }
  try {
    final PerfEvent logLayoutState=logger != null ? LogTreePopulator.populatePerfEventFromLogger(c,logger,logger.newPerformanceEvent(c,EVENT_RESUME_CALCULATE_LAYOUT_STATE)) : null;
    if (logLayoutState != null) {
      logLayoutState.markerAnnotate(PARAM_COMPONENT,component.getSimpleName());
      logLayoutState.markerAnnotate(PARAM_LAYOUT_STATE_SOURCE,sourceToString(source));
    }
    resumeCreateAndMeasureTreeForComponent(c,component,widthSpec,heightSpec,null,layoutState.mLayoutRoot,layoutState.mDiffTreeRoot,logLayoutState);
    layoutState.mIsPartialLayoutState=false;
    setSizeAfterMeasureAndCollectResults(c,layoutState);
    if (logLayoutState != null) {
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
