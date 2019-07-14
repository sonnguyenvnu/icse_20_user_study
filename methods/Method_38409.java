/** 
 * Lookups the decorator for given decorator path. Returns  {@code null} if decorator is not registered, indicating that content should beread using the dispatcher.
 */
public char[] lookupDecoratorContent(final String path){
  if (contentMap != null) {
    final char[] data=contentMap.get(path);
    if (data != null) {
      return data;
    }
    final File file=filesMap.get(path);
    if (file != null) {
      try {
        return FileUtil.readChars(file);
      }
 catch (      IOException e) {
        throw new DecoraException("Unable to read Decrator files",e);
      }
    }
  }
  return null;
}
