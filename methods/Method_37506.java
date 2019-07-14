/** 
 * Returns parent for the file. The method correctly processes "." and ".." in  {@link File} names. The nameremains relative if was relative before. Returns  {@code null} if the {@link File} has no parent.
 * @param file {@link File}
 * @return {@code null} if the {@link File} has no parent.
 */
public static File getParentFile(final File file){
  int skipCount=ZERO;
  File parentFile=file;
  while (true) {
    parentFile=parentFile.getParentFile();
    if (parentFile == null) {
      return null;
    }
    if (StringPool.DOT.equals(parentFile.getName())) {
      continue;
    }
    if (StringPool.DOTDOT.equals(parentFile.getName())) {
      skipCount++;
      continue;
    }
    if (skipCount > ZERO) {
      skipCount--;
      continue;
    }
    return parentFile;
  }
}
