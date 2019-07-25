public boolean has(String name){
  reload();
  return objs.containsKey(name);
}
