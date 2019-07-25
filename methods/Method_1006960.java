/** 
 * Increment the counter.
 * @param delta the amount by which to increment the counter.
 */
final public void increment(int delta){
  AtomicInteger count=getCounter();
  count.addAndGet(delta);
}
