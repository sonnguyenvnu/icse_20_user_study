/** 
 * Return the delegate logger instance if set. Otherwise, return a  {@link NOPLogger}instance.
 */
public Logger delegate(){
  if (_delegate != null) {
    return _delegate;
  }
  if (createdPostInitialization) {
    return NOPLogger.NOP_LOGGER;
  }
 else {
    return getEventRecordingLogger();
  }
}
