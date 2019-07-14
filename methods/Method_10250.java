private static void test8(List<String> stringCollection){
  Supplier<Stream<String>> streamSupplier=() -> stringCollection.stream().filter(s -> s.startsWith("a"));
  streamSupplier.get().anyMatch(s -> true);
  streamSupplier.get().noneMatch(s -> true);
}
