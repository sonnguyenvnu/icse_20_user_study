private Set<Class<?>> init(Object implementation){
  return init(implementation,Types.getAllInterfaces(implementation.getClass()));
}
