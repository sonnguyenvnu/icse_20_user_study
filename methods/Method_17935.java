protected static <E>EventHandler<E> newEventHandler(Component c,int id,Object[] params){
  final EventHandler<E> eventHandler=new EventHandler<>(c,id,params);
  if (c.getScopedContext() != null && c.getScopedContext().getComponentTree() != null) {
    c.getScopedContext().getComponentTree().recordEventHandler(c,eventHandler);
  }
  return eventHandler;
}
