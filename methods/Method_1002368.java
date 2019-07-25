/** 
 * Allows consuming histogram. Reference to the histogram should not be cached in any way because it is reused for performance reasons. Histogram passed to the consumer includes stable, consistent view of all values accumulated since last harvest or overflow. This method is thread safe.
 * @param consumer consumer for a harvested histogram
 */
public void harvest(Consumer<AbstractHistogram> consumer){
  AbstractHistogram current=claim(_current);
  _current.set(claim(_inactive));
  try {
    consumer.accept(current);
  }
 catch (  Throwable t) {
    LOG.error("failed to consume histogram for latencies metric",t);
  }
 finally {
    current.reset();
    _inactive.set(current);
  }
}
