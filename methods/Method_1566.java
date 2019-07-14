/** 
 * Determine if this bitmap is reusable (i.e.) if subsequent  {@link #get(int)} requests canuse this value. The bitmap is reusable if - it has not already been recycled AND - it is mutable
 * @param value the value to test for reusability
 * @return true, if the bitmap can be reused
 */
@Override protected boolean isReusable(Bitmap value){
  Preconditions.checkNotNull(value);
  return !value.isRecycled() && value.isMutable();
}
