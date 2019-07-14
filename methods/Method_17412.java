/** 
 * Returns the input stream for the raw file. 
 */
private InputStream openFile() throws IOException {
  Path file=Paths.get(filePath);
  if (Files.exists(file)) {
    return Files.newInputStream(file);
  }
  InputStream input=getClass().getResourceAsStream(filePath);
  checkArgument(input != null,"Could not find file: " + filePath);
  return input;
}
