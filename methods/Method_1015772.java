public static <T>String print(Collection<T> objs){
  return objs == null ? "null" : objs.stream().map(Objects::toString).collect(Collectors.joining(", "));
}
