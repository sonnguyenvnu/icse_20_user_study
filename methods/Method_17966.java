void measure(int widthSpec,int heightSpec,int[] measureOutput,boolean forceLayout){
  assertMainThread();
  Component component=null;
  TreeProps treeProps=null;
synchronized (this) {
    mIsMeasuring=true;
    mWidthSpec=widthSpec;
    mHeightSpec=heightSpec;
    setBestMainThreadLayoutAndReturnOldLayout();
    final boolean shouldCalculateNewLayout=!isCompatibleSpec(mMainThreadLayoutState,mWidthSpec,mHeightSpec) || (!mMainThreadLayoutState.isForComponentId(mRoot.getId()) && !isPendingLayoutCompatible());
    if (mForceLayout || forceLayout || shouldCalculateNewLayout) {
      component=mRoot.makeShallowCopy();
      treeProps=TreeProps.copy(mRootTreeProps);
      mForceLayout=false;
    }
  }
  if (component != null) {
    if (mMainThreadLayoutState != null) {
synchronized (this) {
        mMainThreadLayoutState=null;
      }
    }
    LayoutState localLayoutState=calculateLayoutState(mContext,component,widthSpec,heightSpec,mIsLayoutDiffingEnabled,null,treeProps,CalculateLayoutSource.MEASURE,null);
    if (localLayoutState == null) {
      throw new IllegalStateException("LayoutState cannot be null for measure, this means a LayoutStateFuture was released incorrectly.");
    }
    final List<Component> components;
    @Nullable final Map<String,Component> attachables;
synchronized (this) {
      final StateHandler layoutStateStateHandler=localLayoutState.consumeStateHandler();
      components=new ArrayList<>(localLayoutState.getComponents());
      attachables=localLayoutState.consumeAttachables();
      if (layoutStateStateHandler != null) {
        mStateHandler.commit(layoutStateStateHandler,mNestedTreeResolutionExperimentEnabled);
      }
      localLayoutState.clearComponents();
      mMainThreadLayoutState=localLayoutState;
    }
    final AttachDetachHandler attachDetachHandler=mContext.getAttachDetachHandler();
    if (attachDetachHandler != null) {
      attachDetachHandler.onAttached(attachables);
    }
 else     if (attachables != null) {
      mContext.getOrCreateAttachDetachHandler().onAttached(attachables);
    }
    bindEventAndTriggerHandlers(components);
    mLithoView.setMountStateDirty();
    dispatchNewLayoutStateReady();
  }
  measureOutput[0]=mMainThreadLayoutState.getWidth();
  measureOutput[1]=mMainThreadLayoutState.getHeight();
  int layoutScheduleType=SCHEDULE_NONE;
  Component root=null;
  TreeProps rootTreeProps=null;
synchronized (this) {
    mIsMeasuring=false;
    if (mScheduleLayoutAfterMeasure != SCHEDULE_NONE) {
      layoutScheduleType=mScheduleLayoutAfterMeasure;
      mScheduleLayoutAfterMeasure=SCHEDULE_NONE;
      root=mRoot.makeShallowCopy();
      rootTreeProps=TreeProps.copy(mRootTreeProps);
    }
  }
  if (layoutScheduleType != SCHEDULE_NONE) {
    setRootAndSizeSpecInternal(root,SIZE_UNINITIALIZED,SIZE_UNINITIALIZED,layoutScheduleType == SCHEDULE_LAYOUT_ASYNC,null,CalculateLayoutSource.MEASURE,null,rootTreeProps);
  }
}
