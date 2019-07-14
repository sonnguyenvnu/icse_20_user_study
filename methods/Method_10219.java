private static void testLines() throws IOException {
  try (Stream<String> stream=Files.lines(Paths.get("res/nashorn1.js"))){
    stream.filter(line -> line.contains("print")).map(String::trim).forEach(System.out::println);
  }
 }
