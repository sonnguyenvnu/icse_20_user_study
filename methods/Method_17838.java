public <E>EventHandler<E> newEventHandler(int id,Object[] params){
  return new EventHandler<>(mComponentScope,id,params);
}
