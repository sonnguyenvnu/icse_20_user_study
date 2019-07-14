private void reportDetached(boolean detachedForActivity){
  boolean wasDetachedForActivity=reportedState == ReportedState.ACTIVITY_STOPPED;
  if (detachedForActivity) {
    reportedState=ReportedState.ACTIVITY_STOPPED;
  }
 else {
    reportedState=ReportedState.VIEW_DETACHED;
  }
  if (wasDetachedForActivity && !detachedForActivity) {
    attachListener.onViewDetachAfterStop();
  }
 else {
    attachListener.onDetached(detachedForActivity);
  }
}
