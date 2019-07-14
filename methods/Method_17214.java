/** 
 * Returns the ratio of cache requests which were hits. This is defined as {@code hitCount / requestCount}, or  {@code 1.0} when {@code requestCount == 0}. Note that {@code hitRate + missRate =~ 1.0}.
 * @return the ratio of cache requests which were hits
 */
@NonNegative public double hitRate(){
  long requestCount=requestCount();
  return (requestCount == 0) ? 1.0 : (double)hitCount / requestCount;
}
