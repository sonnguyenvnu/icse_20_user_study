/** 
 * Mount the layoutState on the pre-set HostView.
 * @param layoutState
 * @param localVisibleRect If this variable is null, then mount everything, since incrementalmount is not enabled. Otherwise mount only what the rect (in local coordinates) contains
 * @param processVisibilityOutputs whether to process visibility outputs as part of the mount
 */
void mount(LayoutState layoutState,Rect localVisibleRect,boolean processVisibilityOutputs){
  assertMainThread();
  if (layoutState == null) {
    throw new IllegalStateException("Trying to mount a null layoutState");
  }
  final ComponentTree componentTree=mLithoView.getComponentTree();
  final boolean isIncrementalMountEnabled=localVisibleRect != null;
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    final StringBuilder sectionName=new StringBuilder(isIncrementalMountEnabled ? "incrementalMount" : "mount").append("_").append(componentTree.getSimpleName());
    final String logTag=componentTree.getContext().getLogTag();
    if (logTag != null) {
      sectionName.append("_").append(logTag);
    }
    ComponentsSystrace.beginSectionWithArgs(sectionName.toString()).arg("treeId",layoutState.getComponentTreeId()).flush();
  }
  final ComponentsLogger logger=componentTree.getContext().getLogger();
  final int componentTreeId=layoutState.getComponentTreeId();
  if (componentTreeId != mLastMountedComponentTreeId) {
    mLastMountedLayoutState=null;
  }
  final PerfEvent mountPerfEvent=logger == null ? null : LogTreePopulator.populatePerfEventFromLogger(componentTree.getContext(),logger,logger.newPerformanceEvent(componentTree.getContext(),EVENT_MOUNT));
  if (mIsDirty) {
    updateTransitions(layoutState,componentTree);
    suppressInvalidationsOnHosts(true);
    if (mountPerfEvent != null) {
      mountPerfEvent.markerPoint("PREPARE_MOUNT_START");
    }
    prepareMount(layoutState,mountPerfEvent);
    if (mountPerfEvent != null) {
      mountPerfEvent.markerPoint("PREPARE_MOUNT_END");
    }
  }
  mMountStats.reset();
  if (mountPerfEvent != null && logger.isTracing(mountPerfEvent)) {
    mMountStats.enableLogging();
  }
  if (!isIncrementalMountEnabled || !performIncrementalMount(layoutState,localVisibleRect,processVisibilityOutputs)) {
    final MountItem rootMountItem=mIndexToItemMap.get(ROOT_HOST_ID);
    for (int i=0, size=layoutState.getMountableOutputCount(); i < size; i++) {
      final LayoutOutput layoutOutput=layoutState.getMountableOutputAt(i);
      final Component component=layoutOutput.getComponent();
      if (isTracing) {
        ComponentsSystrace.beginSection(component.getSimpleName());
      }
      final MountItem currentMountItem=getItemAt(i);
      final boolean isMounted=currentMountItem != null;
      final boolean isMountable=!isIncrementalMountEnabled || isMountedHostWithChildContent(currentMountItem) || Rect.intersects(localVisibleRect,layoutOutput.getBounds()) || isAnimationLocked(i) || (currentMountItem != null && currentMountItem == rootMountItem);
      if (isMountable && !isMounted) {
        mountLayoutOutput(i,layoutOutput,layoutState);
        if (isAnimationLocked(i) && isIncrementalMountEnabled && component.hasChildLithoViews()) {
          final View view=(View)getItemAt(i).getContent();
          mountViewIncrementally(view,false);
        }
      }
 else       if (!isMountable && isMounted) {
        unmountItem(i,mHostsByMarker);
      }
 else       if (isMounted) {
        if (mIsDirty) {
          final boolean useUpdateValueFromLayoutOutput=mLastMountedLayoutState != null && mLastMountedLayoutState.getId() == layoutState.getPreviousLayoutStateId();
          final long startTime=System.nanoTime();
          final TransitionId transitionId=currentMountItem.getTransitionId();
          final boolean itemUpdated=updateMountItemIfNeeded(layoutOutput,layoutState,currentMountItem,useUpdateValueFromLayoutOutput,componentTreeId,i);
          if (itemUpdated) {
            maybeRemoveAnimatingMountContent(transitionId);
          }
          if (mMountStats.isLoggingEnabled) {
            if (itemUpdated) {
              mMountStats.updatedNames.add(component.getSimpleName());
              mMountStats.updatedTimes.add((System.nanoTime() - startTime) / NS_IN_MS);
              mMountStats.updatedCount++;
            }
 else {
              mMountStats.noOpCount++;
            }
          }
        }
        if (isIncrementalMountEnabled && component.hasChildLithoViews()) {
          mountItemIncrementally(currentMountItem,processVisibilityOutputs);
        }
      }
      if (isTracing) {
        ComponentsSystrace.endSection();
      }
    }
    if (isIncrementalMountEnabled) {
      setupPreviousMountableOutputData(layoutState,localVisibleRect);
    }
  }
  maybeUpdateAnimatingMountContent();
  if (shouldAnimateTransitions(layoutState) && hasTransitionsToAnimate()) {
    mTransitionManager.runTransitions();
  }
  if (processVisibilityOutputs) {
    ComponentsSystrace.beginSection("processVisibilityOutputs");
    processVisibilityOutputs(layoutState,localVisibleRect,mountPerfEvent);
    ComponentsSystrace.endSection();
  }
  mRootTransition=null;
  mTransitionsHasBeenCollected=false;
  final boolean wasDirty=mIsDirty;
  mIsDirty=false;
  mNeedsRemount=false;
  mIsFirstMountOfComponentTree=false;
  if (localVisibleRect != null) {
    mPreviousLocalVisibleRect.set(localVisibleRect);
  }
  mLastMountedLayoutState=null;
  mLastMountedComponentTreeId=componentTreeId;
  mLastMountedLayoutState=layoutState;
  processTestOutputs(layoutState);
  suppressInvalidationsOnHosts(false);
  if (logger != null) {
    logMountPerfEvent(logger,mountPerfEvent,wasDirty);
  }
  if (isTracing) {
    ComponentsSystrace.endSection();
  }
}
