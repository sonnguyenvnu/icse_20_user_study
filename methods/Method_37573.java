/** 
 * Reads path content.
 */
public static String readString(final Path path) throws IOException {
  try (BufferedReader reader=Files.newBufferedReader(path,StandardCharsets.UTF_8)){
    StringWriter writer=new StringWriter();
    StreamUtil.copy(reader,writer);
    return writer.toString();
  }
 }
