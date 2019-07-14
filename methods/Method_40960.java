/** 
 * Returns whether any configured delay function can be applied for an execution result.
 * @see #withDelay(DelayFunction)
 * @see #withDelayOn(DelayFunction,Class)
 * @see #withDelayWhen(DelayFunction,Object)
 */
public boolean canApplyDelayFn(R result,Throwable failure){
  return (delayResult == null || delayResult.equals(result)) && (delayFailure == null || (failure != null && delayFailure.isAssignableFrom(failure.getClass())));
}
