/** 
 * Set BK's zookeeper session timeout in seconds.
 * @param sessionTimeout session timeout for the ZK Client used by BK Client, in seconds.
 * @return distributed log configuration
 * @see #getBKClientZKSessionTimeoutMilliSeconds()
 */
public DistributedLogConfiguration _XXXXX_(int sessionTimeout){
  setProperty(BKDL_BKCLIENT_ZK_SESSION_TIMEOUT,sessionTimeout);
  return this;
}