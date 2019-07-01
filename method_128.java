/** 
 * Set max number of entries for each scan to read last record.
 * @param numEntries number of entries to scan
 * @return distributedlog configuration.
 */
public DistributedLogConfiguration _XXXXX_(int numEntries){
  setProperty(BKDL_MAX_NUM_ENTRIES_PER_READ_LAST_RECORD_SCAN,numEntries);
  return this;
}