private static void testWriter() throws IOException {
  Path path=Paths.get("res/output.js");
  try (BufferedWriter writer=Files.newBufferedWriter(path)){
    writer.write("print('Hello World');");
  }
 }
