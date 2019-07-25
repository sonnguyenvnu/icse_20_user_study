/** 
 * Return the relative path. Path elements are separated with / char.
 * @param baseDir a parent directory of  {@code file}
 * @param file the file to get the relative path
 * @return the relative path
 */
public static String relativize(Path baseDir,Path file){
  String path=baseDir.toUri().relativize(file.toUri()).getPath();
  if (!"/".equals(baseDir.getFileSystem().getSeparator())) {
    return path.replace(baseDir.getFileSystem().getSeparator(),"/");
  }
 else {
    return path;
  }
}
