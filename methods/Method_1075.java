/** 
 * Gets the array of first-available image requests. <p> For performance reasons, the array is not deep-copied, but only stored by reference. Please don't modify.
 */
@Nullable public REQUEST[] getFirstAvailableImageRequests(){
  return mMultiImageRequests;
}
