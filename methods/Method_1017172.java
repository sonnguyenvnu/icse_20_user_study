private <T>List<T> run(final Function<SuggestBackend,T> op){
  return ImmutableList.copyOf(backends.stream().map(op).iterator());
}
