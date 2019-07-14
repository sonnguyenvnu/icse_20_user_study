public boolean isStarted(){
  return !shuttingDown && !closed && !isInStandbyMode() && initialStart != null;
}
