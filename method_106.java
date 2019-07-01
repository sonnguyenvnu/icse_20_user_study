/** 
 * Given a basedir and a child file, return the relative path to the child.
 * @param basedir the basedir.
 * @param file    the file to get the relative path for.
 * @return the relative path to the child. (NOTE: this path will NOT start with a file separator character)
 */
public static String _XXXXX_(Path basedir,Path file){
  if (basedir.isAbsolute() && !file.isAbsolute()) {
    return basedir.normalize().relativize(file.toAbsolutePath()).toString();
  }
 else   if (!basedir.isAbsolute() && file.isAbsolute()) {
    return basedir.toAbsolutePath().relativize(file.normalize()).toString();
  }
 else {
    return basedir.normalize().relativize(file.normalize()).toString();
  }
}