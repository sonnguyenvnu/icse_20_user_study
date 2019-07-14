/** 
 * This implementation is an inexact estimate. It's just the product of a constant ( {@link #ENTRY_SIZE_ESTIMATE} times the array size.The exact size could be calculated by iterating over the list and summing the remaining size of each StaticBuffer in each Entry.
 * @return crude approximation of actual size
 */
@Override public int getByteSize(){
  return size() * ENTRY_SIZE_ESTIMATE;
}
