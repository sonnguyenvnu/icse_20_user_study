/** 
 * Allocate bits for UID according to delta seconds & workerId & sequence<br> <b>Note that: </b>The highest bit will always be 0 for sign
 * @param deltaSeconds
 * @param workerId
 * @param sequence
 * @return
 */
public long allocate(long deltaSeconds,long workerId,long sequence){
  return (deltaSeconds << timestampShift) | (workerId << workerIdShift) | sequence;
}
