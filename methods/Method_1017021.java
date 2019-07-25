public static Groups combine(final Groups first,final Groups... other){
  final ImmutableSet.Builder<String> all=ImmutableSet.<String>builder().addAll(first.groups);
  Arrays.stream(other).forEach(all::addAll);
  return new Groups(all.build());
}
