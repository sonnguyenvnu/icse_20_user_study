private void copyPendingStateTransitions(@Nullable Map<String,List<Transition>> pendingStateUpdateTransitions){
  if (pendingStateUpdateTransitions == null || pendingStateUpdateTransitions.isEmpty()) {
    return;
  }
synchronized (this) {
    maybeInitPendingStateUpdateTransitions();
    mPendingStateUpdateTransitions.putAll(pendingStateUpdateTransitions);
  }
}
