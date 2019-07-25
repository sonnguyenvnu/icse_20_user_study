public ProxyBuilder<T> implementing(Class<?>... interfaces){
  List<Class<?>> list=this.interfaces;
  for (  Class<?> i : interfaces) {
    if (!i.isInterface()) {
      throw new IllegalArgumentException("Not an interface: " + i.getName());
    }
    if (!list.contains(i)) {
      list.add(i);
    }
  }
  return this;
}
