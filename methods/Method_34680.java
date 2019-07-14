/** 
 * If  {@link #execute()} or {@link #queue()} fails in any way then this method will be invoked to provide an opportunity to return a fallback response.<p> This should do work that does not require network transport to produce. <p> In other words, this should be a static or cached result that can immediately be returned upon failure. <p> If network traffic is wanted for fallback (such as going to MemCache) then the fallback implementation should invoke another  {@link HystrixCommand} instance that protects against that networkaccess and possibly has another level of fallback that does not involve network access. <p> DEFAULT BEHAVIOR: It throws UnsupportedOperationException.
 * @return R or throw UnsupportedOperationException if not implemented
 */
protected R getFallback(){
  throw new UnsupportedOperationException("No fallback available.");
}
