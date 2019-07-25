/** 
 * Create an empty  {@link DispatchWorkProcessor} instance with the given scheduler,capacity hint (expected number of items cached until consumption) and if an error should be delayed.
 * @param < T > the input and output value type
 * @param scheduler the scheduler to use for the {@link Observer}s to be notified on
 * @param capacityHint the expected number of items to be cached until consumption
 * @param delayErrors if true, errors are delivered after items have been consumed
 * @return the new DispatchWorkSubject instance
 */
public static <T>DispatchWorkProcessor<T> create(Scheduler scheduler,int capacityHint,boolean delayErrors){
  return new DispatchWorkProcessor<T>(capacityHint,delayErrors,scheduler,false);
}
