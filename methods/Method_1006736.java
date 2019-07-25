/** 
 * Add a log entry with sort key for deterministic ordering.
 * @param sortKey The sort key for the log entry.
 * @param msg The message.
 * @param e The  {@link Throwable} that was thrown.
 * @return a child log node, which can be used to add sub-entries.
 */
public LogNode log(final String sortKey,final String msg,final Throwable e){
  return addChild(sortKey,msg,-1L).addChild(e);
}
