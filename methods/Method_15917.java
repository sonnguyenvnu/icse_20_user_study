@Override protected Class<?> resolveInterface(Class<?> type){
  Class<?> classToCreate;
  if (type == List.class || type == Collection.class || type == Iterable.class) {
    classToCreate=ArrayList.class;
  }
 else   if (type == Map.class) {
    classToCreate=HashMap.class;
  }
 else   if (type == SortedSet.class) {
    classToCreate=TreeSet.class;
  }
 else   if (type == Set.class) {
    classToCreate=HashSet.class;
  }
 else {
    classToCreate=entityFactory.getInstanceType(type);
  }
  return classToCreate;
}
