/** 
 * Sets the  {@code timeout} for executions. Executions that exceed this timeout are not interrupted, but are recordedas failures once they naturally complete.
 * @throws NullPointerException if {@code timeout} is null
 * @throws IllegalArgumentException if {@code timeout} <= 0
 */
public CircuitBreaker<R> withTimeout(Duration timeout){
  Assert.notNull(timeout,"timeout");
  Assert.isTrue(timeout.toNanos() > 0,"timeout must be greater than 0");
  this.timeout=timeout;
  return this;
}
