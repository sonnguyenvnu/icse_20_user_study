/** 
 * internal catchup method.
 * @param checkState true to check if the current state allow to perform a catchup
 */
private void catchup(boolean checkState){
  StreamAction state=getServiceState();
  boolean canCatchup=true;
  if (!checkState) {
    Log.i(LOG_TAG,"catchup  without checking state ");
  }
 else {
    Log.i(LOG_TAG,"catchup with state " + state + " CurrentActivity " + VectorApp.getCurrentActivity());
    canCatchup=(state == StreamAction.CATCHUP) || (state == StreamAction.PAUSE) || ((StreamAction.START == state) && (null == VectorApp.getCurrentActivity()));
  }
  if (canCatchup) {
    if (mSessions != null) {
      for (      MXSession session : mSessions) {
        session.catchupEventStream();
      }
    }
 else {
      Log.i(LOG_TAG,"catchup no session");
    }
    setServiceState(StreamAction.CATCHUP);
  }
 else {
    Log.i(LOG_TAG,"No catchup is triggered because there is already a running event thread");
  }
}
