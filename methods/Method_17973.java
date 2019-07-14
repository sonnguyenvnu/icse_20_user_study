void updateStateInternal(boolean isAsync,String attribution){
  final Component root;
  final @Nullable TreeProps rootTreeProps;
synchronized (this) {
    if (mRoot == null) {
      return;
    }
    if (mIsMeasuring) {
      if (mScheduleLayoutAfterMeasure == SCHEDULE_LAYOUT_SYNC) {
        return;
      }
      mScheduleLayoutAfterMeasure=isAsync ? SCHEDULE_LAYOUT_ASYNC : SCHEDULE_LAYOUT_SYNC;
      return;
    }
    root=mRoot.makeShallowCopy();
    rootTreeProps=TreeProps.copy(mRootTreeProps);
  }
  setRootAndSizeSpecInternal(root,SIZE_UNINITIALIZED,SIZE_UNINITIALIZED,isAsync,null,isAsync ? CalculateLayoutSource.UPDATE_STATE_ASYNC : CalculateLayoutSource.UPDATE_STATE_SYNC,attribution,rootTreeProps);
}
