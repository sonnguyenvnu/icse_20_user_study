/** 
 * Get a description of the current location in a suspended thread. Format: class.method:translated_line_number
 * @param t a suspended thread
 * @return descriptive string for the given location
 */
protected String currentLocation(ThreadReference t){
  try {
    if (!t.isSuspended() || t.frameCount() == 0) {
      return "";
    }
    return locationToString(t.frame(0).location());
  }
 catch (  IncompatibleThreadStateException ex) {
    logitse(ex);
    return "";
  }
}
