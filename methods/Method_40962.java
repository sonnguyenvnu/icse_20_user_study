/** 
 * Returns the function that determines the next delay given a failed attempt with the given  {@link Throwable}.
 * @see #withDelay(DelayFunction)
 * @see #withDelayOn(DelayFunction,Class)
 * @see #withDelayWhen(DelayFunction,Object)
 */
public DelayFunction<R,? extends Throwable> getDelayFn(){
  return delayFn;
}
