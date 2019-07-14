public void registerStateListener(StateListener l){
  stateListeners.add(l);
  if (currentState != 0)   l.onStateChanged(currentState);
  if (signalBarCount != 0)   l.onSignalBarsCountChanged(signalBarCount);
}
