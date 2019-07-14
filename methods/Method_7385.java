@Override public void onSignalBarCountChanged(int newCount){
  signalBarCount=newCount;
  for (int a=0; a < stateListeners.size(); a++) {
    StateListener l=stateListeners.get(a);
    l.onSignalBarsCountChanged(newCount);
  }
}
