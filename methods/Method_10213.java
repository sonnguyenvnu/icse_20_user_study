private static void testReaderLines() throws IOException {
  Path path=Paths.get("res/nashorn1.js");
  try (BufferedReader reader=Files.newBufferedReader(path)){
    long countPrints=reader.lines().filter(line -> line.contains("print")).count();
    System.out.println(countPrints);
  }
 }
