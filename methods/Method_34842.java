/** 
 * Increment the counter in the current bucket by one for the given  {@link HystrixRollingNumberEvent} type.<p> The  {@link HystrixRollingNumberEvent} must be a "counter" type <code>HystrixRollingNumberEvent.isCounter() == true</code>.
 * @param type HystrixRollingNumberEvent defining which counter to increment
 */
public void increment(HystrixRollingNumberEvent type){
  getCurrentBucket().getAdder(type).increment();
}
