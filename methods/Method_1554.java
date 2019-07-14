/** 
 * Determines if the current length of the bucket (free + used) exceeds the max length specified
 */
public boolean isMaxLengthExceeded(){
  return (mInUseLength + getFreeListSize() > mMaxLength);
}
