public void handleRun(){
  if (isDebuggerEnabled()) {
    if (debugger.isStarted()) {
      debugger.stopDebug();
    }
    debugger.continueDebug();
  }
 else {
    handleLaunch(false,false);
  }
}
