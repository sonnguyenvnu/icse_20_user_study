public void stop(){
  assertLive();
  windowResizeReg.removeHandler();
  windowResizeReg=null;
  for (  CommandSet commandSet : commandSets) {
    commandSet.attachEventReg.removeHandler();
  }
  commandSets=null;
}
