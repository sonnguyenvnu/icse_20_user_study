/** 
 * Accepts if a file is going to be watched.
 */
protected boolean acceptFile(final File file){
  if (!file.isFile()) {
    return false;
  }
  String fileName=file.getName();
  if (ignoreDotFiles) {
    if (fileName.startsWith(StringPool.DOT)) {
      return false;
    }
  }
  if (patterns == null) {
    return true;
  }
  return Wildcard.matchOne(fileName,patterns) != -1;
}
