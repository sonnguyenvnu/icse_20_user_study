/** 
 * Removes all serialized ASTs from the on-disk cache.
 * @return {@code true} if all cached AST files were removed
 */
public boolean clearDiskCache(){
  try {
    _.deleteDirectory(new File(Analyzer.self.cacheDir));
    return true;
  }
 catch (  Exception x) {
    LOG.log(Level.SEVERE,"Failed to clear disk cache: " + x);
    return false;
  }
}
