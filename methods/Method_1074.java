/** 
 * Sets the array of first-available image requests that will be probed in order. <p> For performance reasons, the array is not deep-copied, but only stored by reference. Please don't modify once submitted.
 * @param tryCacheOnlyFirst if set, bitmap cache only requests will be tried in order beforethe supplied requests.
 */
public BUILDER setFirstAvailableImageRequests(REQUEST[] firstAvailableImageRequests,boolean tryCacheOnlyFirst){
  Preconditions.checkArgument(firstAvailableImageRequests == null || firstAvailableImageRequests.length > 0,"No requests specified!");
  mMultiImageRequests=firstAvailableImageRequests;
  mTryCacheOnlyFirst=tryCacheOnlyFirst;
  return getThis();
}
