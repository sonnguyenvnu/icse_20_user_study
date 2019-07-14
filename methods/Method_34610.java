/** 
 * Whether the 'circuit-breaker' is open meaning that <code>execute()</code> will immediately return the <code>getFallback()</code> response and not attempt a HystrixCommand execution. 4 columns are ForcedOpen | ForcedClosed | CircuitBreaker open due to health ||| Expected Result T | T | T ||| OPEN (true) T | T | F ||| OPEN (true) T | F | T ||| OPEN (true) T | F | F ||| OPEN (true) F | T | T ||| CLOSED (false) F | T | F ||| CLOSED (false) F | F | T ||| OPEN (true) F | F | F ||| CLOSED (false)
 * @return boolean
 */
public boolean isCircuitBreakerOpen(){
  return properties.circuitBreakerForceOpen().get() || (!properties.circuitBreakerForceClosed().get() && circuitBreaker.isOpen());
}
