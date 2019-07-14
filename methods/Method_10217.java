private static void testFind() throws IOException {
  Path start=Paths.get("");
  int maxDepth=5;
  try (Stream<Path> stream=Files.find(start,maxDepth,(path,attr) -> String.valueOf(path).endsWith(".js"))){
    String joined=stream.sorted().map(String::valueOf).collect(Collectors.joining("; "));
    System.out.println("find(): " + joined);
  }
 }
