/** 
 * Set Log Flush Timeout in seconds.
 * @param logFlushTimeoutSeconds log flush timeout.
 * @return distributed log configuration
 * @see #getLogFlushTimeoutSeconds()
 */
public DistributedLogConfiguration _XXXXX_(int logFlushTimeoutSeconds){
  setProperty(BKDL_LOG_FLUSH_TIMEOUT,logFlushTimeoutSeconds);
  return this;
}