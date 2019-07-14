/** 
 * Creates the specified directory, along with all parent paths if necessary
 * @param directory directory to be created
 * @throws CreateDirectoryException
 */
public static void mkdirs(File directory) throws CreateDirectoryException {
  if (directory.exists()) {
    if (directory.isDirectory()) {
      return;
    }
    if (!directory.delete()) {
      throw new CreateDirectoryException(directory.getAbsolutePath(),new FileDeleteException(directory.getAbsolutePath()));
    }
  }
  if (!directory.mkdirs() && !directory.isDirectory()) {
    throw new CreateDirectoryException(directory.getAbsolutePath());
  }
}
