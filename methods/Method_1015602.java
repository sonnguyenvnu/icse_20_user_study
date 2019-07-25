/** 
 * updates the sequence number to the maximum between them
 * @param otherSequenceNumber   the sequence number received
 */
public void update(long otherSequenceNumber){
  sequenceNumber=Math.max(sequenceNumber,otherSequenceNumber + 1);
}
