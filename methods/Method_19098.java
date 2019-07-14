@UiThread private void applyChangeSetsToTargetUIThreadOnly(int source,@Nullable String attribution,@Nullable Section oldSection){
  assertMainThread();
  if (mUseBackgroundChangeSets) {
    throw new IllegalStateException("Cannot use UIThread-only variant when background change sets are enabled.");
  }
  final boolean isTracing=ComponentsSystrace.isTracing();
  if (isTracing) {
    ComponentsSystrace.beginSection("applyChangeSetsToTargetUIThreadOnly");
  }
  try {
    final List<ChangeSet> changeSets;
    final Section currentSection;
synchronized (this) {
      if (mReleased) {
        return;
      }
      changeSets=new ArrayList<>(mPendingChangeSets);
      mPendingChangeSets.clear();
      currentSection=mCurrentSection;
    }
    applyChangeSetsToTargetUnchecked(currentSection,oldSection,changeSets,source,attribution);
    maybeDispatchFocusRequests();
  }
  finally {
    if (isTracing) {
      ComponentsSystrace.endSection();
    }
  }
}
