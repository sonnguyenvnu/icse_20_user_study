synchronized @Nullable void consumeStateUpdateTransitions(List<Transition> outList,@Nullable String logContext){
  if (mStateHandler != null) {
    mStateHandler.consumePendingStateUpdateTransitions(outList,logContext);
  }
}
