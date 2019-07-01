/** 
 * Internal copy directory method.
 * @param srcDir           the validated source directory, must not be {@code null}
 * @param destDir          the validated destination directory, must not be {@code null}
 * @param filter           the filter to apply, null means copy all directories and files
 * @param preserveFileDate whether to preserve the file date
 * @param exclusionList    List of files and directories to exclude from the copy, may be null
 * @throws IOException if an error occurs
 * @since 1.1
 */
private static void _XXXXX_(final File srcDir,final File destDir,final FileFilter filter,final boolean preserveFileDate,final List<String> exclusionList) throws IOException {
  final File[] srcFiles=filter == null ? srcDir.listFiles() : srcDir.listFiles(filter);
  if (srcFiles == null) {
    throw new IOException("Failed to list contents of " + srcDir);
  }
  if (destDir.exists()) {
    if (destDir.isDirectory() == false) {
      throw new IOException("Destination '" + destDir + "' exists but is not a directory");
    }
  }
 else {
    if (!destDir.mkdirs() && !destDir.isDirectory()) {
      throw new IOException("Destination '" + destDir + "' directory cannot be created");
    }
  }
  if (destDir.canWrite() == false) {
    throw new IOException("Destination '" + destDir + "' cannot be written to");
  }
  for (  final File srcFile : srcFiles) {
    final File dstFile=new File(destDir,srcFile.getName());
    if (exclusionList == null || !exclusionList.contains(srcFile.getCanonicalPath())) {
      if (srcFile.isDirectory()) {
        _XXXXX_(srcFile,dstFile,filter,preserveFileDate,exclusionList);
      }
 else {
        doCopyFile(srcFile,dstFile,preserveFileDate);
      }
    }
  }
  if (preserveFileDate) {
    destDir.setLastModified(srcDir.lastModified());
  }
}