private static Set<Class<?>> createSet(Class<?>[] classes){
  if (classes == null || classes.length == 0) {
    return Collections.emptySet();
  }
  for (  Class<?> category : classes) {
    if (category == null) {
      throw new NullPointerException("has null category");
    }
  }
  return classes.length == 1 ? Collections.<Class<?>>singleton(classes[0]) : new LinkedHashSet<Class<?>>(Arrays.asList(classes));
}
