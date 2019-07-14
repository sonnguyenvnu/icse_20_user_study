void processVisibilityOutputs(LayoutState layoutState,Rect localVisibleRect,@Nullable PerfEvent mountPerfEvent){
  assertMainThread();
  if (localVisibleRect == null) {
    return;
  }
  if (mountPerfEvent != null) {
    mountPerfEvent.markerPoint("VISIBILITY_HANDLERS_START");
  }
  final boolean isDoingPerfLog=mMountStats.isLoggingEnabled;
  final boolean isTracing=ComponentsSystrace.isTracing();
  final long totalStartTime=isDoingPerfLog ? System.nanoTime() : 0L;
  for (int j=0, size=layoutState.getVisibilityOutputCount(); j < size; j++) {
    final VisibilityOutput visibilityOutput=layoutState.getVisibilityOutputAt(j);
    if (isTracing) {
      final String componentName=visibilityOutput.getComponent() != null ? visibilityOutput.getComponent().getSimpleName() : "Unknown";
      ComponentsSystrace.beginSection("visibilityHandlers:" + componentName);
    }
    final long handlerStartTime=isDoingPerfLog ? System.nanoTime() : 0;
    final EventHandler<VisibleEvent> visibleHandler=visibilityOutput.getVisibleEventHandler();
    final EventHandler<FocusedVisibleEvent> focusedHandler=visibilityOutput.getFocusedEventHandler();
    final EventHandler<UnfocusedVisibleEvent> unfocusedHandler=visibilityOutput.getUnfocusedEventHandler();
    final EventHandler<FullImpressionVisibleEvent> fullImpressionHandler=visibilityOutput.getFullImpressionEventHandler();
    final EventHandler<InvisibleEvent> invisibleHandler=visibilityOutput.getInvisibleEventHandler();
    final EventHandler<VisibilityChangedEvent> visibilityChangedHandler=visibilityOutput.getVisibilityChangedEventHandler();
    final long visibilityOutputId=visibilityOutput.getId();
    final Rect visibilityOutputBounds=visibilityOutput.getBounds();
    boolean boundsIntersect=sTempRect.setIntersect(visibilityOutputBounds,localVisibleRect);
    final boolean isCurrentlyVisible=boundsIntersect && isInVisibleRange(visibilityOutput,visibilityOutputBounds,sTempRect);
    VisibilityItem visibilityItem=mVisibilityIdToItemMap.get(visibilityOutputId);
    if (visibilityItem != null) {
      final String previousGlobalKey=visibilityItem.getGlobalKey();
      final String currentGlobalKey=visibilityOutput.getComponent() != null ? visibilityOutput.getComponent().getGlobalKey() : null;
      final boolean hasGlobalKeyChanged=previousGlobalKey != null && !previousGlobalKey.equals(currentGlobalKey);
      if (!hasGlobalKeyChanged) {
        visibilityItem.setUnfocusedHandler(unfocusedHandler);
        visibilityItem.setInvisibleHandler(invisibleHandler);
      }
      if (!isCurrentlyVisible || hasGlobalKeyChanged) {
        if (visibilityItem.getInvisibleHandler() != null) {
          EventDispatcherUtils.dispatchOnInvisible(visibilityItem.getInvisibleHandler());
        }
        if (visibilityChangedHandler != null) {
          EventDispatcherUtils.dispatchOnVisibilityChanged(visibilityChangedHandler,0,0,0f,0f);
        }
        if (visibilityItem.isInFocusedRange()) {
          visibilityItem.setFocusedRange(false);
          if (visibilityItem.getUnfocusedHandler() != null) {
            EventDispatcherUtils.dispatchOnUnfocused(visibilityItem.getUnfocusedHandler());
          }
        }
        mVisibilityIdToItemMap.remove(visibilityOutputId);
        visibilityItem=null;
      }
 else {
        visibilityItem.setDoNotClearInThisPass(mIsDirty);
      }
    }
    if (isCurrentlyVisible) {
      if (visibilityItem == null) {
        final String globalKey=visibilityOutput.getComponent() != null ? visibilityOutput.getComponent().getGlobalKey() : null;
        visibilityItem=new VisibilityItem(globalKey,invisibleHandler,unfocusedHandler,visibilityChangedHandler);
        visibilityItem.setDoNotClearInThisPass(mIsDirty);
        mVisibilityIdToItemMap.put(visibilityOutputId,visibilityItem);
        if (visibleHandler != null) {
          EventDispatcherUtils.dispatchOnVisible(visibleHandler);
        }
      }
      if (focusedHandler != null || unfocusedHandler != null) {
        if (isInFocusedRange(visibilityOutputBounds,sTempRect)) {
          if (!visibilityItem.isInFocusedRange()) {
            visibilityItem.setFocusedRange(true);
            if (focusedHandler != null) {
              EventDispatcherUtils.dispatchOnFocused(focusedHandler);
            }
          }
        }
 else {
          if (visibilityItem.isInFocusedRange()) {
            visibilityItem.setFocusedRange(false);
            if (unfocusedHandler != null) {
              EventDispatcherUtils.dispatchOnUnfocused(unfocusedHandler);
            }
          }
        }
      }
      if (fullImpressionHandler != null && !visibilityItem.isInFullImpressionRange()) {
        visibilityItem.setVisibleEdges(visibilityOutputBounds,sTempRect);
        if (visibilityItem.isInFullImpressionRange()) {
          EventDispatcherUtils.dispatchOnFullImpression(fullImpressionHandler);
        }
      }
      if (visibilityChangedHandler != null) {
        final int visibleWidth=sTempRect.right - sTempRect.left;
        final int visibleHeight=sTempRect.bottom - sTempRect.top;
        EventDispatcherUtils.dispatchOnVisibilityChanged(visibilityChangedHandler,visibleWidth,visibleHeight,100f * visibleWidth / visibilityOutputBounds.width(),100f * visibleHeight / visibilityOutputBounds.height());
      }
    }
    if (isDoingPerfLog) {
      final String componentName=visibilityOutput.getComponent() != null ? visibilityOutput.getComponent().getSimpleName() : "Unknown";
      mMountStats.visibilityHandlerTimes.add((System.nanoTime() - handlerStartTime) / NS_IN_MS);
      mMountStats.visibilityHandlerNames.add(componentName);
    }
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
  if (mIsDirty) {
    clearVisibilityItems();
  }
  if (isDoingPerfLog) {
    mMountStats.visibilityHandlersTotalTime=(System.nanoTime() - totalStartTime) / NS_IN_MS;
  }
  if (mountPerfEvent != null) {
    mountPerfEvent.markerPoint("VISIBILITY_HANDLERS_END");
  }
}
