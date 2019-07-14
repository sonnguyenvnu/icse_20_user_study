/** 
 * Fetch some action config and consumes it.
 */
public <T extends ActionConfig>void with(final Class<T> actionConfigType,final Consumer<T> actionConfigConsumer){
  final T actionConfig=(T)lookup(actionConfigType);
  actionConfigConsumer.accept(actionConfig);
}
