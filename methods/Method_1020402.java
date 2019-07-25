/** 
 * Returns a new  {@link Builder} with all given options set.
 * @param traceOptions the given options set.
 * @return a new {@code Builder} with all given options set.
 * @since 0.5
 */
public static Builder builder(TraceOptions traceOptions){
  return new Builder(traceOptions.options);
}
