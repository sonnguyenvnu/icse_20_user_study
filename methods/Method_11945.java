/** 
 * Gets the runtime for the test.
 * @param unit time unit for returned runtime
 * @return runtime measured during the test
 */
public long runtime(TimeUnit unit){
  return unit.convert(getNanos(),TimeUnit.NANOSECONDS);
}
