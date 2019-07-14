private void onAction(Action action){
  mState=mState.next(action);
  if (mState == State.FINAL) {
    onDetected();
    mState=mState.next(null);
  }
}
