/** 
 * Finds a file inside a directory structure. Will also look for the file inside nested directories.
 * @param filename      the name of the file that should be found
 * @param rootDirectory the rootDirectory that will be searched
 * @return the path to the first file that matches the defined conditions
 */
public static Optional<Path> find(String filename,Path rootDirectory){
  try (Stream<Path> pathStream=Files.walk(rootDirectory)){
    return pathStream.filter(Files::isRegularFile).filter(f -> f.getFileName().toString().equals(filename)).findFirst();
  }
 catch (  UncheckedIOException|IOException ex) {
    LOGGER.error("Error trying to locate the file " + filename + " inside the directory " + rootDirectory);
  }
  return Optional.empty();
}
