/** 
 * Creates single directory.
 * @throws IOException if cannot create directory.
 */
public static File mkdir(final File dir) throws IOException {
  if (dir.exists()) {
    checkIsDirectory(dir);
    return dir;
  }
  return checkCreateDirectory(dir);
}
