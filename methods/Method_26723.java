private static <T>ImmutableList<T> cast(Iterable<?> elements,Class<T> clazz){
  ImmutableList.Builder<T> builder=ImmutableList.builder();
  for (  Object element : elements) {
    builder.add(clazz.cast(element));
  }
  return builder.build();
}
