/** 
 * Registers all handler methods on  {@code object} to receive events.Handler methods are selected and classified using this EventBus's {@link SubscriberFindingStrategy}; the default strategy is the {@link AnnotatedSubscriberFinder}.
 * @param object object whose handler methods should be registered.
 */
public void register(Object object){
  subscribeAll(finder.findAllSubscribers(object));
}
