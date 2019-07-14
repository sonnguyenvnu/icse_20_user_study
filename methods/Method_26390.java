public static WellKnownMutability fromFlags(ErrorProneFlags flags){
  List<String> immutable=flags.getList("Immutable:KnownImmutable").orElse(ImmutableList.of());
  List<String> unsafe=flags.getList("Immutable:KnownUnsafe").orElse(ImmutableList.of());
  return new WellKnownMutability(immutable,unsafe);
}
