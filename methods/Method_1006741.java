/** 
 * Add a log entry.
 * @param e The  {@link Throwable} that was thrown.
 * @return a child log node, which can be used to add sub-entries.
 */
public LogNode log(final Throwable e){
  return log("Exception thrown").addChild(e);
}
