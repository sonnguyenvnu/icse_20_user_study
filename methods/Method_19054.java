protected static <E>EventHandler<E> newEventHandler(Section c,int id,Object[] params){
  final EventHandler eventHandler=new EventHandler<E>(c,id,params);
  recordEventHandler(c,eventHandler);
  return eventHandler;
}
