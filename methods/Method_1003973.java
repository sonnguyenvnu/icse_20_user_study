/** 
 * Instructs the proxy that cache setup is complete, and the proxy instance should begin caching and delegating uncached calls.  After this is called, any subsequent calls to any of the cache setup methods will result in an  {@link IllegalStateException}.
 */
public void prepare(){
  Preconditions.checkState(!methodCaches.isEmpty(),"At least one method must be cached.");
  Preconditions.checkState(recordMode,"prepare() may only be invoked once.");
  recordMode=false;
}
