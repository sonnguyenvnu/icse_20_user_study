/** 
 * Sets the  {@code delay} to wait in open state before transitioning to half-open.
 * @throws NullPointerException if {@code delay} is null
 * @throws IllegalArgumentException if {@code delay} < 0
 */
public CircuitBreaker<R> withDelay(Duration delay){
  Assert.notNull(delay,"delay");
  Assert.isTrue(delay.toNanos() >= 0,"delay must not be negative");
  this.delay=delay;
  return this;
}
