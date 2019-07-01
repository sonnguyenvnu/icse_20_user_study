/** 
 * Enable/Disable immediate flush.
 * @param enabled flag to enable/disable immediate flush.
 * @return configuration instance.
 * @see #getImmediateFlushEnabled()
 */
public DistributedLogConfiguration _XXXXX_(boolean enabled){
  setProperty(BKDL_ENABLE_IMMEDIATE_FLUSH,enabled);
  return this;
}