/** 
 * Register a  {@link HystrixConcurrencyStrategy} implementation as a global override of any injected or default implementations.
 * @param impl {@link HystrixConcurrencyStrategy} implementation
 * @throws IllegalStateException if called more than once or after the default was initialized (if usage occurs before trying to register)
 */
public void registerConcurrencyStrategy(HystrixConcurrencyStrategy impl){
  if (!concurrencyStrategy.compareAndSet(null,impl)) {
    throw new IllegalStateException("Another strategy was already registered.");
  }
}
