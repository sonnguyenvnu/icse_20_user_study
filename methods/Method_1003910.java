/** 
 * Accumulates a value and increments the event counter.
 * @param value Value to accumulate.
 */
public synchronized void accumulate(long value){
  totalEvents.increment();
  totalValue.add(value);
}
