@Override public Groups groups(){
  final ImmutableSet.Builder<String> groups=ImmutableSet.builder();
  for (  final T member : members) {
    groups.addAll(member.groups());
  }
  return new Groups(groups.build());
}
