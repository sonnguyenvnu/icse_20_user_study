private Set<Class<?>> filter(Set<Class<?>> types){
  return types.stream().filter(c -> !isExcluded(c.getCanonicalName())).collect(Collectors.toSet());
}
