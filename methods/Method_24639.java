/** 
 * Get the class name of the current this object in a suspended thread.
 * @param t a suspended thread
 * @return the class name of this
 */
protected String thisName(ThreadReference t){
  try {
    if (!t.isSuspended() || t.frameCount() == 0) {
      return "";
    }
    ObjectReference ref=t.frame(0).thisObject();
    return ref == null ? "" : ref.referenceType().name();
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
    return "";
  }
}
