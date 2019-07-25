/** 
 * Returns an array of all files in the given directory matching.
 */
public static Path[] files(Path from,DirectoryStream.Filter<Path> filter) throws IOException {
  try (DirectoryStream<Path> stream=Files.newDirectoryStream(from,filter)){
    return toArray(stream);
  }
 }
