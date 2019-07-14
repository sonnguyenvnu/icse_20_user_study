private void handleSignalBarsChange(int count){
  if (listener != null)   listener.onSignalBarCountChanged(count);
}
