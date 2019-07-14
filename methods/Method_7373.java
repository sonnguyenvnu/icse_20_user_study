protected void dispatchStateChanged(int state){
  if (BuildVars.LOGS_ENABLED) {
    FileLog.d("== Call " + getCallID() + " state changed to " + state + " ==");
  }
  currentState=state;
  if (USE_CONNECTION_SERVICE && state == STATE_ESTABLISHED && systemCallConnection != null) {
    systemCallConnection.setActive();
  }
  for (int a=0; a < stateListeners.size(); a++) {
    StateListener l=stateListeners.get(a);
    l.onStateChanged(state);
  }
}
