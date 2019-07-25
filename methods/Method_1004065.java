/** 
 * Returns a counter with values incremented by the numbers of the given counter. It is up to the implementation whether this counter instance is modified or a new instance is returned.
 * @param counter number of additional total and covered items
 * @return counter instance with incremented values
 */
public CounterImpl increment(final ICounter counter){
  return increment(counter.getMissedCount(),counter.getCoveredCount());
}
