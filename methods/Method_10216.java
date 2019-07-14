private static void testWalk() throws IOException {
  Path start=Paths.get("");
  int maxDepth=5;
  try (Stream<Path> stream=Files.walk(start,maxDepth)){
    String joined=stream.map(String::valueOf).filter(path -> path.endsWith(".js")).collect(Collectors.joining("; "));
    System.out.println("walk(): " + joined);
  }
 }
