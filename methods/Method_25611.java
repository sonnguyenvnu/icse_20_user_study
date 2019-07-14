/** 
 * Returns a timing span for the given  {@link Suppressible}. 
 */
public AutoCloseable timingSpan(Suppressible suppressible){
  return sharedState.timings.span(suppressible);
}
