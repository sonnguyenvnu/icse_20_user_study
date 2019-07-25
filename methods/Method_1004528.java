/** 
 * Create a new  {@link FibonacciPollInterval} with the same time unit but with a different offset
 * @return The same of instance of {@link FibonacciPollInterval}
 */
public FibonacciPollInterval offset(int offset){
  return new FibonacciPollInterval(offset,timeUnit);
}
