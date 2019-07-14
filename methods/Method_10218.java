private static void testList() throws IOException {
  try (Stream<Path> stream=Files.list(Paths.get(""))){
    String joined=stream.map(String::valueOf).filter(path -> !path.startsWith(".")).sorted().collect(Collectors.joining("; "));
    System.out.println("list(): " + joined);
  }
 }
