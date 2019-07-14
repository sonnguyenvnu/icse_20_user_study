/** 
 * ?????ID
 * @return id
 */
public synchronized long nextId(){
  long currentStamp=getTimeMill();
  if (currentStamp < lastStamp) {
    throw new RuntimeException("Clock moved backwards.");
  }
  if (currentStamp == lastStamp) {
    sequence=(sequence + 1) & this.maxSequenceValue;
    if (sequence == 0L) {
      lastStamp=tilNextMillis();
    }
  }
 else {
    sequence=0L;
  }
  lastStamp=currentStamp;
  return (currentStamp - START_STAMP) << timestampBitLeftOffset | idcId << idcBitLeftOffset | machineId << machineBitLeftOffset | sequence;
}
