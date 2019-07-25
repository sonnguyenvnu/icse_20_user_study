/** 
 * Returns an array of all files in the given directory matching the glob.
 */
public static Path[] files(Path directory,String glob) throws IOException {
  try (DirectoryStream<Path> stream=Files.newDirectoryStream(directory,glob)){
    return toArray(stream);
  }
 }
