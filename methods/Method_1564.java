/** 
 * Gets the bucketed size of the value. We don't check the 'validity' of the value (beyond the not-null check). That's handled in  {@link #isReusable(Bitmap)}
 * @param value the value
 * @return bucketed size of the value
 */
@Override protected int getBucketedSizeForValue(Bitmap value){
  Preconditions.checkNotNull(value);
  return value.getAllocationByteCount();
}
