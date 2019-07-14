/** 
 * Creates and returns a Counter based on the initial value
 * @return The counter created by this config
 */
public Counter createCounter(){
  return new CounterImpl(initialValue);
}
