@Override public Stream<AllowableAction> stream(){
  return Stream.concat(Stream.of(this),getSubActions().stream().flatMap(AllowableAction::stream));
}
