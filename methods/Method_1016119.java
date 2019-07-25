/** 
 * Returns normalized file without redundant parts in its path.
 * @param file file to normalize
 * @return normalized file
 */
public static File normalize(final File file){
  try {
    return file != null ? file.getCanonicalFile() : file;
  }
 catch (  final IOException e) {
    return file;
  }
}
