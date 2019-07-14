/** 
 * Pre-allocate the mount content of all MountSpec in this tree. Must be called after layout is created.
 */
@ThreadSafe(enableChecks=false) private void preAllocateMountContent(boolean shouldPreallocatePerMountSpec){
  final LayoutState toPrePopulate;
synchronized (this) {
    if (mMainThreadLayoutState != null) {
      toPrePopulate=mMainThreadLayoutState;
    }
 else     if (mBackgroundLayoutState != null) {
      toPrePopulate=mBackgroundLayoutState;
    }
 else {
      return;
    }
  }
  final ComponentsLogger logger=mContext.getLogger();
  final PerfEvent event=logger != null ? LogTreePopulator.populatePerfEventFromLogger(mContext,logger,logger.newPerformanceEvent(mContext,EVENT_PRE_ALLOCATE_MOUNT_CONTENT)) : null;
  toPrePopulate.preAllocateMountContent(shouldPreallocatePerMountSpec);
  if (logger != null) {
    logger.logPerfEvent(event);
  }
}
