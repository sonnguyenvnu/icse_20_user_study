/** 
 * Cleans a directory without deleting it.
 * @param destDir destination to clean.
 * @throws IOException if something went wrong.
 */
public static void cleanDir(final File destDir) throws IOException {
  checkExists(destDir);
  checkIsDirectory(destDir);
  File[] files=destDir.listFiles();
  if (files == null) {
    throw new IOException("Failed to list contents of: " + destDir);
  }
  IOException exception=null;
  for (  File file : files) {
    try {
      if (file.isDirectory()) {
        deleteDir(file);
      }
 else {
        file.delete();
      }
    }
 catch (    IOException ioex) {
      exception=ioex;
      continue;
    }
  }
  if (exception != null) {
    throw exception;
  }
}
