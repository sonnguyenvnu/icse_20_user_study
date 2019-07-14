private static void testReader() throws IOException {
  Path path=Paths.get("res/nashorn1.js");
  try (BufferedReader reader=Files.newBufferedReader(path)){
    System.out.println(reader.readLine());
  }
 }
