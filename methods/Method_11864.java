private static Class<?> findExpectedType(Class<?> fromClass){
  for (Class<?> c=fromClass; c != Object.class; c=c.getSuperclass()) {
    for (    Method method : MethodSorter.getDeclaredMethods(c)) {
      if (isMatchesSafelyMethod(method)) {
        return method.getParameterTypes()[0];
      }
    }
  }
  throw new Error("Cannot determine correct type for matchesSafely() method.");
}
