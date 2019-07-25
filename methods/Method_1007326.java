public int lookup(String name){
  return containsKey(name) ? get(name) : -1;
}
