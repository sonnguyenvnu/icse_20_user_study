/** 
 * Returns true if this future was cancelled with  {@code mayInterruptIfRunning} set to {@code true}.
 * @since 14.0
 */
protected final boolean wasInterrupted(){
  final Object localValue=value;
  return (localValue instanceof Cancellation) && ((Cancellation)localValue).wasInterrupted;
}
