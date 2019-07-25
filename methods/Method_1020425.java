/** 
 * Constructs a new  {@code TracezZPageHandler}.
 * @param runningSpanStore the instance of the {@code RunningSpanStore} to be used.
 * @param sampledSpanStore the instance of the {@code SampledSpanStore} to be used.
 * @return a new {@code TracezZPageHandler}.
 */
static TracezZPageHandler create(@javax.annotation.Nullable RunningSpanStore runningSpanStore,@javax.annotation.Nullable SampledSpanStore sampledSpanStore){
  return new TracezZPageHandler(runningSpanStore,sampledSpanStore);
}
