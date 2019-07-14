/** 
 * Must be triggered by a particular  {@link KCVSLog} when it is closed so that this log can be removed from the listof open logs.
 * @param log
 */
synchronized void closedLog(KCVSLog log){
  KCVSLog l=openLogs.remove(log.getName());
  assert l == log;
}
