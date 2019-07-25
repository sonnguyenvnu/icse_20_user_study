public static <T extends Grouped>GroupSet<T> build(Collection<T> configured,Optional<List<String>> defaultBackends){
  final Map<String,Set<T>> mappings=buildBackends(configured);
  final Set<T> defaults=buildDefaults(mappings,defaultBackends);
  return new GroupSet<>(ImmutableSet.copyOf(configured),mappings,ImmutableSet.copyOf(defaults));
}
