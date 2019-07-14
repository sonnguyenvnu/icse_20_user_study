private void postNewChangeSets(Throwable tracedThrowable,final int source,@Nullable final String attribution,@Nullable final Section oldSection){
  if (mUseBackgroundChangeSets) {
    applyChangeSetsToTargetBackgroundAllowed(source,attribution,oldSection);
    return;
  }
  if (isMainThread()) {
    try {
      applyChangeSetsToTargetUIThreadOnly(source,attribution,oldSection);
    }
 catch (    IndexOutOfBoundsException e) {
      throw new RuntimeException(getDebugInfo(this) + e.getMessage(),e);
    }
  }
 else {
    String tag=EMPTY_STRING;
    if (mMainThreadHandler.isTracing()) {
      tag="SectionTree.postNewChangeSets - " + mTag;
    }
    mMainThreadHandler.post(new ThreadTracingRunnable(tracedThrowable){
      @Override public void tracedRun(      Throwable tracedThrowable){
        final SectionTree tree=SectionTree.this;
        try {
          tree.applyChangeSetsToTargetUIThreadOnly(source,attribution,oldSection);
        }
 catch (        IndexOutOfBoundsException e) {
          throw new RuntimeException(getDebugInfo(tree) + e.getMessage(),e);
        }
      }
    }
,tag);
  }
}
