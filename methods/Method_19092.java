private void applyNewChangeSet(@ApplyNewChangeSet int source,@Nullable String attribution,Throwable tracedThrowable){
  if (attribution == null) {
    attribution=mTag;
  }
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    if (attribution != null) {
      ComponentsSystrace.beginSection("extra:" + attribution);
    }
    final String name;
synchronized (this) {
      name=mNextSection != null ? mNextSection.getSimpleName() : "<null>";
    }
    ComponentsSystrace.beginSection(name + "_applyNewChangeSet_" + SectionsLogEventUtils.applyNewChangeSetSourceToString(source));
  }
  if (SectionsDebug.ENABLED) {
    final String name;
synchronized (this) {
      name=mNextSection != null ? mNextSection.getSimpleName() : "<null>";
    }
    Log.d(SectionsDebug.TAG,"=== NEW CHANGE SET (" + SectionsLogEventUtils.applyNewChangeSetSourceToString(source) + ", S: " + name + ", Tree: " + hashCode() + ") ====");
  }
  try {
    Section currentRoot;
    Section nextRoot;
    StateUpdatesHolder pendingStateUpdates;
    final ComponentsLogger logger;
synchronized (this) {
      if (mReleased) {
        return;
      }
      currentRoot=copy(mCurrentSection,true);
      nextRoot=copy(mNextSection,false);
      logger=mContext.getLogger();
      pendingStateUpdates=mPendingStateUpdates.copy();
    }
    final PerfEvent logEvent=SectionsLogEventUtils.getSectionsPerformanceEvent(mContext,EVENT_SECTIONS_SET_ROOT,currentRoot,nextRoot);
    final boolean enableStats=logger != null && logEvent != null && logger.isTracing(logEvent);
    if (logEvent != null) {
      logEvent.markerAnnotate(PARAM_ATTRIBUTION,attribution);
      logEvent.markerAnnotate(PARAM_SECTION_SET_ROOT_SOURCE,SectionsLogEventUtils.applyNewChangeSetSourceToString(source));
      logEvent.markerAnnotate(PARAM_SET_ROOT_ON_BG_THREAD,!ThreadUtils.isMainThread());
    }
    clearUnusedTriggerHandlers();
    while (nextRoot != null) {
      if (isTracing) {
        ComponentsSystrace.beginSection("calculateNewChangeSet");
      }
      final ChangeSetState changeSetState=calculateNewChangeSet(mContext,currentRoot,nextRoot,pendingStateUpdates.mAllStateUpdates,mSectionsDebugLogger,mTag,enableStats);
      if (isTracing) {
        ComponentsSystrace.endSection();
      }
      final boolean changeSetIsValid;
      Section oldRoot=null;
      Section newRoot=null;
synchronized (this) {
        boolean currentNotNull=currentRoot != null;
        boolean instanceCurrentNotNull=mCurrentSection != null;
        boolean currentIsSame=(currentNotNull && instanceCurrentNotNull && currentRoot.getId() == mCurrentSection.getId()) || (!currentNotNull && !instanceCurrentNotNull);
        boolean nextIsSame=(mNextSection != null && nextRoot.getId() == mNextSection.getId());
        changeSetIsValid=currentIsSame && nextIsSame && isStateUpdateCompleted(pendingStateUpdates);
        if (changeSetIsValid) {
          oldRoot=mCurrentSection;
          newRoot=nextRoot;
          mCurrentSection=newRoot;
          mNextSection=null;
          mPendingStateUpdates.removeCompletedStateUpdates(pendingStateUpdates);
          mPendingChangeSets.add(changeSetState.getChangeSet());
          if (oldRoot != null) {
            unbindOldComponent(oldRoot);
            oldRoot.release();
          }
          bindTriggerHandler(newRoot);
        }
      }
      if (changeSetIsValid) {
        if (newRoot != null) {
          bindNewComponent(newRoot);
        }
        final List<Section> removedComponents=changeSetState.getRemovedComponents();
        for (int i=0, size=removedComponents.size(); i < size; i++) {
          final Section removedComponent=removedComponents.get(i);
          releaseRange(mLastRanges.remove(removedComponent.getGlobalKey()));
        }
        mEventHandlersController.clearUnusedEventHandlers();
        postNewChangeSets(tracedThrowable,source,attribution,oldRoot);
      }
synchronized (this) {
        SectionsPools.release(pendingStateUpdates);
        if (mReleased) {
          return;
        }
        currentRoot=copy(mCurrentSection,true);
        nextRoot=copy(mNextSection,false);
        if (nextRoot != null) {
          pendingStateUpdates=mPendingStateUpdates.copy();
        }
      }
    }
    if (logger != null && logEvent != null) {
      logger.logPerfEvent(logEvent);
    }
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
      if (attribution != null) {
        ComponentsSystrace.endSection();
      }
    }
  }
}
