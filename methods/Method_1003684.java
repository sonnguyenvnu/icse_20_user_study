/** 
 * Finds the “directory” on the classpath that contains a file called  {@code .ratpack}. <p> Calling this method is equivalent to calling  {@link #find(String) findBaseDir(".ratpack")}.
 * @return a base dir
 * @see #find(String)
 */
public static Path find(){
  return find(DEFAULT_BASE_DIR_MARKER_FILE_PATH);
}
