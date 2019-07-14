/** 
 * Register a  {@link HystrixEventNotifier} implementation as a global override of any injected or default implementations.
 * @param impl {@link HystrixEventNotifier} implementation
 * @throws IllegalStateException if called more than once or after the default was initialized (if usage occurs before trying to register)
 */
public void registerEventNotifier(HystrixEventNotifier impl){
  if (!notifier.compareAndSet(null,impl)) {
    throw new IllegalStateException("Another strategy was already registered.");
  }
}
