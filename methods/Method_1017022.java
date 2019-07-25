private Set<T> find(String group){
  final Set<T> result=groups.get(group);
  if (result == null) {
    return ImmutableSet.of();
  }
  return result;
}
