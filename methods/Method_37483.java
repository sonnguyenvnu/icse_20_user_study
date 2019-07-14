/** 
 * Creates all directories at once.
 * @param dirs Directories to make.
 * @throws IOException if cannot create directory.
 */
public static File mkdirs(final File dirs) throws IOException {
  if (dirs.exists()) {
    checkIsDirectory(dirs);
    return dirs;
  }
  return checkCreateDirectory(dirs);
}
