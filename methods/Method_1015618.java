@SafeVarargs protected static int accumulate(ToIntFunction<Table> func,Collection<? extends Entry>... entries){
  return Stream.of(entries).flatMap(Collection::stream).map(entry -> entry.msgs).filter(Objects::nonNull).mapToInt(func).sum();
}
