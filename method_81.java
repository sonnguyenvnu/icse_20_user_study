/** 
 * Set the long poll time out for read last add confirmed requests, in milliseconds.
 * @param readAheadLongPollTimeout long poll timeout in milliseconds
 * @return distributed log configuration
 * @see #getReadLACLongPollTimeout()
 */
public DistributedLogConfiguration _XXXXX_(int readAheadLongPollTimeout){
  setProperty(BKDL_READLACLONGPOLL_TIMEOUT,readAheadLongPollTimeout);
  return this;
}