private void bindEventAndTriggerHandlers(List<Component> components){
  clearUnusedTriggerHandlers();
  for (  final Component component : components) {
    mEventHandlersController.bindEventHandlers(component.getScopedContext(),component,component.getGlobalKey());
    bindTriggerHandler(component);
  }
  mEventHandlersController.clearUnusedEventHandlers();
}
