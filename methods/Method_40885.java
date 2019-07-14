/** 
 * Executes the  {@code runnable} until successful or until the configured policies are exceeded.
 * @throws NullPointerException if the {@code runnable} is null
 * @throws FailsafeException if the {@code supplier} fails with a checked Exception or if interrupted whilewaiting to perform a retry.
 * @throws CircuitBreakerOpenException if a configured circuit is open.
 */
public void run(CheckedRunnable runnable){
  call(execution -> Functions.supplierOf(runnable));
}
