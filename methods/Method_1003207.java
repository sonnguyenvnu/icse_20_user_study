/** 
 * Allows the start value, increment, min value and max value to be updated atomically, including atomic validation. Useful because setting these attributes one after the other could otherwise result in an invalid sequence state (e.g. min value > max value, start value < min value, etc).
 * @param startValue the new start value (<code>null</code> if no change)
 * @param minValue the new min value (<code>null</code> if no change)
 * @param maxValue the new max value (<code>null</code> if no change)
 * @param increment the new increment (<code>null</code> if no change)
 */
public synchronized void modify(Long startValue,Long minValue,Long maxValue,Long increment){
  if (startValue == null) {
    startValue=this.value;
  }
  if (minValue == null) {
    minValue=this.minValue;
  }
  if (maxValue == null) {
    maxValue=this.maxValue;
  }
  if (increment == null) {
    increment=this.increment;
  }
  if (!isValid(startValue,minValue,maxValue,increment)) {
    throw DbException.get(ErrorCode.SEQUENCE_ATTRIBUTES_INVALID,getName(),String.valueOf(startValue),String.valueOf(minValue),String.valueOf(maxValue),String.valueOf(increment));
  }
  this.value=startValue;
  this.valueWithMargin=startValue;
  this.minValue=minValue;
  this.maxValue=maxValue;
  this.increment=increment;
}
