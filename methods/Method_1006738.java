/** 
 * Add a log entry.
 * @param msg The message.
 * @param elapsedTimeNanos The elapsed time.
 * @param e The  {@link Throwable} that was thrown.
 * @return a child log node, which can be used to add sub-entries.
 */
public LogNode log(final String msg,final long elapsedTimeNanos,final Throwable e){
  return addChild("",msg,elapsedTimeNanos).addChild(e);
}
