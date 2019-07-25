/** 
 * Returns an array of all files in the given directory.
 */
public static Path[] files(Path directory) throws IOException {
  try (DirectoryStream<Path> stream=Files.newDirectoryStream(directory)){
    return toArray(stream);
  }
 }
