/** 
 * Positions to a DLSN greater than the current value. <p>This may not correspond to an actual LogRecord, its just used by the positioning logic to position the reader.
 * @return the next DLSN
 */
public DLSN _XXXXX_(){
  return new DLSN(logSegmentSequenceNo,entryId,slotId + 1);
}