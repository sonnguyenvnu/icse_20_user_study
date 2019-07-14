/** 
 * Returns the ratio of cache requests which were misses. This is defined as {@code missCount / requestCount}, or  {@code 0.0} when {@code requestCount == 0}. Note that  {@code hitRate + missRate =~ 1.0}. Cache misses include all requests which weren't cache hits, including requests which resulted in either successful or failed loading attempts, and requests which waited for other threads to finish loading. It is thus the case that  {@code missCount &gt;= loadSuccessCount + loadFailureCount}. Multiple concurrent misses for the same key will result in a single load operation.
 * @return the ratio of cache requests which were misses
 */
@NonNegative public double missRate(){
  long requestCount=requestCount();
  return (requestCount == 0) ? 0.0 : (double)missCount / requestCount;
}
