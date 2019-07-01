/** 
 * Set the interval between successive executions of the operation timeout monitor. The value in seconds. Every X seconds, all outstanding add and read operations are checked to see if they have been running for longer than their configured timeout. Any that have been will be errored out. <p>This timeout should be set to a value which is a fraction of the values of {@link #getAddEntryQuorumTimeout},  {@link #getAddEntryTimeout} and {@link #getReadEntryTimeout}, so that these timeouts run in a timely fashion.
 * @param timeoutInterval The timeout monitor interval, in seconds
 * @return client configuration
 */
public ClientConfiguration _XXXXX_(long timeoutInterval){
  setProperty(TIMEOUT_MONITOR_INTERVAL_SEC,Long.toString(timeoutInterval));
  return this;
}