protected static <E>EventHandler<E> newEventHandler(SectionContext c,int id,Object[] params){
  final EventHandler eventHandler=c.newEventHandler(id,params);
  recordEventHandler(c.getSectionScope(),eventHandler);
  return eventHandler;
}
