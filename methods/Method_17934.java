protected static <E>EventHandler<E> newEventHandler(ComponentContext c,int id,Object[] params){
  final EventHandler<E> eventHandler=c.newEventHandler(id,params);
  if (c.getComponentTree() != null) {
    c.getComponentTree().recordEventHandler(c.getComponentScope(),eventHandler);
  }
  return eventHandler;
}
