public ObjectProxy fetch(String name){
  checkBuffer();
  return objs.get(name);
}
