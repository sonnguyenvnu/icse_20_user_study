@Override public final Set<AsciiString> names(){
  if (isEmpty()) {
    return ImmutableSet.of();
  }
  final ImmutableSet.Builder<AsciiString> builder=ImmutableSet.builder();
  HeaderEntry e=head.after;
  while (e != head) {
    builder.add(e.getKey());
    e=e.after;
  }
  return builder.build();
}
