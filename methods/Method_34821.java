/** 
 * Register a  {@link HystrixPropertiesStrategy} implementation as a global override of any injected or default implementations.
 * @param impl {@link HystrixPropertiesStrategy} implementation
 * @throws IllegalStateException if called more than once or after the default was initialized (if usage occurs before trying to register)
 */
public void registerPropertiesStrategy(HystrixPropertiesStrategy impl){
  if (!propertiesFactory.compareAndSet(null,impl)) {
    throw new IllegalStateException("Another strategy was already registered.");
  }
}
