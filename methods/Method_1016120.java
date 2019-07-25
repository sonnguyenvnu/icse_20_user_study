/** 
 * Returns whether both files represent the same path in file system or not.
 * @param file1 first file to be compared
 * @param file2 second file to be compared
 * @return true if both files represent the same path in file system, false otherwise
 */
public static boolean equals(final File file1,final File file2){
  if (file1 == null && file2 == null) {
    return true;
  }
 else {
    final boolean notNull=file1 != null && file2 != null;
    try {
      return notNull && file1.getCanonicalPath().equals(file2.getCanonicalPath());
    }
 catch (    final IOException e) {
      return notNull && file1.getAbsolutePath().equals(file2.getAbsolutePath());
    }
  }
}
