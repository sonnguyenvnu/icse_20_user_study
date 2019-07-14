/** 
 * Step out if this results in a visible location, otherwise continue.
 */
protected void stepOutIntoViewOrContinue(){
  try {
    List<StackFrame> frames=currentThread.frames();
    if (frames.size() > 1) {
      if (locationIsVisible(frames.get(1).location())) {
        stepOut();
        return;
      }
    }
    continueDebug();
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
  }
}
