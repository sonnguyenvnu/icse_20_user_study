/** 
 * @see #_moveFile(File,File)
 */
public static File moveFile(final File srcFile,final File destFile) throws IOException {
  checkFileCopy(srcFile,destFile);
  _moveFile(srcFile,destFile);
  return destFile;
}
