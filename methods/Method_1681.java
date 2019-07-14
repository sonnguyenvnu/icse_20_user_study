/** 
 * Set whether the request is a prefetch request or not.
 * @param isPrefetch
 */
public void setIsPrefetch(boolean isPrefetch){
  BaseProducerContext.callOnIsPrefetchChanged(setIsPrefetchNoCallbacks(isPrefetch));
}
