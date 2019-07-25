/** 
 * Add a log entry.
 * @param msg The message.
 * @param elapsedTimeNanos The elapsed time.
 * @return a child log node, which can be used to add sub-entries.
 */
public LogNode log(final String msg,final long elapsedTimeNanos){
  return addChild("",msg,elapsedTimeNanos);
}
