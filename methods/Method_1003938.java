/** 
 * Accumulates counter by an offset.  This is is useful for tracking things like latency of operations. TODO(William Farner): Implement a wrapper to SlidingStats that expects to accumulate time, and can convert between time units.
 * @param value The value to accumulate.
 */
public void accumulate(long value){
  total.addAndGet(value);
  events.incrementAndGet();
}
