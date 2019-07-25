/** 
 * Converts an absolute file to a relative one, if possible. Returns the parameter file itself if no shortening is possible. <p> This method works correctly only if directories are sorted decent in their length i.e. /home/user/literature/important before /home/user/literature.
 * @param file the file to be shortened
 * @param directories directories to check
 */
public static Path relativize(Path file,List<Path> directories){
  if (!file.isAbsolute()) {
    return file;
  }
  for (  Path directory : directories) {
    if (file.startsWith(directory)) {
      return directory.relativize(file);
    }
  }
  return file;
}
