/** 
 * Makes a directory, including any necessary but nonexistent parent directories. If a file already exists with specified name but it is not a directory then an IOException is thrown. If the directory cannot be created (or the file already exists but is not a directory) then an IOException is thrown.
 * @param directory directory to create, must not be {@code null}
 * @throws NullPointerException if the directory is {@code null}
 * @throws IOException          if the directory cannot be created or the file already exists but is not a directory
 */
public static void _XXXXX_(final File directory) throws IOException {
  if (directory.exists()) {
    if (!directory.isDirectory()) {
      final String message="File " + directory + " exists and is "+ "not a directory. Unable to create directory.";
      throw new IOException(message);
    }
  }
 else {
    if (!directory.mkdirs()) {
      if (!directory.isDirectory()) {
        final String message="Unable to create directory " + directory;
        throw new IOException(message);
      }
    }
  }
}