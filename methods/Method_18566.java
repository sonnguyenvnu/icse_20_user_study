synchronized void consumePendingStateUpdateTransitions(List<Transition> outList,@Nullable String logContext){
  if (mPendingStateUpdateTransitions == null) {
    return;
  }
  for (  List<Transition> pendingTransitions : mPendingStateUpdateTransitions.values()) {
    for (int i=0, size=pendingTransitions.size(); i < size; i++) {
      TransitionUtils.addTransitions(pendingTransitions.get(i),outList,logContext);
    }
  }
  mPendingStateUpdateTransitions=null;
}
