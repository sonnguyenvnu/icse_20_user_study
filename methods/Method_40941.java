/** 
 * Sets the value of the next bit in the bitset, returning the previous value, else -1 if no previous value was set for the bit.
 * @param value true if positive/success, false if negative/failure
 */
public synchronized int setNext(boolean value){
  int previousValue=-1;
  if (occupiedBits < size)   occupiedBits++;
 else   previousValue=bitSet.get(nextIndex) ? 1 : 0;
  bitSet.set(nextIndex,value);
  nextIndex=indexAfter(nextIndex);
  if (value) {
    if (previousValue != 1)     positives++;
    if (previousValue == 0)     negatives--;
  }
 else {
    if (previousValue != 0)     negatives++;
    if (previousValue == 1)     positives--;
  }
  return previousValue;
}
