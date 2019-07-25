private void push(){
  Class<?> clazz=_current.getValue().getClass();
  if (clazz == DataMap.class) {
    _stack.addLast(new MapState(_current));
  }
 else   if (clazz == DataList.class) {
    _stack.addLast(new ListState(_current));
  }
}
