private static void test7(List<String> stringCollection){
  Stream<String> stream=stringCollection.stream().filter(s -> s.startsWith("a"));
  stream.anyMatch(s -> true);
  stream.noneMatch(s -> true);
}
