protected Set<Class<?>> build(Class<?> concreteClass){
  List<Class<?>> parents=Lists.newLinkedList();
  Set<Class<?>> classes=Sets.newHashSet();
  parents.add(concreteClass);
  while (!parents.isEmpty()) {
    Class<?> clazz=parents.remove(0);
    classes.add(clazz);
    Class<?> parent=clazz.getSuperclass();
    if (parent != null) {
      parents.add(parent);
    }
    Collections.addAll(parents,clazz.getInterfaces());
  }
  return classes;
}
