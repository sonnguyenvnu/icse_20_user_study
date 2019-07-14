@Override public <E>EventHandler<E> newEventHandler(int id,Object[] params){
  final Section section=mScope.get();
  if (section == null) {
    throw new IllegalStateException("Called newEventHandler on a released Section");
  }
  return new EventHandler<>(section,id,params);
}
