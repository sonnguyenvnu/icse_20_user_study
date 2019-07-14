/** 
 * Decrement the mInUseCount field. Used by the pool to update the bucket info when a value was freed, instead of being returned to the bucket's free list
 */
public void decrementInUseCount(){
  Preconditions.checkState(mInUseLength > 0);
  mInUseLength--;
}
