private static boolean hasAssignableTo(Set<Class<?>> assigns,Class<?> to){
  for (  final Class<?> from : assigns) {
    if (to.isAssignableFrom(from)) {
      return true;
    }
  }
  return false;
}
