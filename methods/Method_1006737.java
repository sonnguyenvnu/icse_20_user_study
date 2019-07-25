/** 
 * Add a log entry with sort key for deterministic ordering.
 * @param sortKey The sort key for the log entry.
 * @param msg The message.
 * @return a child log node, which can be used to add sub-entries.
 */
public LogNode log(final String sortKey,final String msg){
  return addChild(sortKey,msg,-1L);
}
