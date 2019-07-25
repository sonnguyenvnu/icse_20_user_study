/** 
 * Syntactic sugar for creating a  {@link IterativePollInterval}.
 * @param function The function to use
 * @return A new instance of {@link IterativePollInterval}
 */
public static IterativePollInterval iterative(Function<Duration,Duration> function){
  return new IterativePollInterval(function);
}
