@SafeVarargs public static <T>List<T> listFilteringNull(T... views){
  return new ImmutableList<>(arrayFilteringNull(views));
}
