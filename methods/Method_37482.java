/** 
 * @see #mkdirs(File)
 */
public static File mkdirs(final String dirs) throws IOException {
  return mkdirs(file(dirs));
}
