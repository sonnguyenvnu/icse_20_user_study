private ComponentTreeHolder createComponentTreeHolder(RenderInfo renderInfo){
  final LithoHandler layoutHandler;
  if (mLayoutHandlerFactory != null) {
    layoutHandler=mLayoutHandlerFactory.createLayoutCalculationHandler(renderInfo);
  }
 else   if (mThreadPoolHandler != null) {
    layoutHandler=mThreadPoolHandler;
  }
 else {
    layoutHandler=null;
  }
  return mComponentTreeHolderFactory.create(renderInfo,layoutHandler,mComponentTreeMeasureListenerFactory,mIncrementalMountEnabled,mMoveLayoutsBetweenThreads,mUseCancelableLayoutFutures);
}
