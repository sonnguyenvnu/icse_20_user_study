/** 
 * Removes all serialized ASTs from the on-disk cache.
 * @return {@code true} if all cached AST files were removed
 */
public boolean clearDiskCache(){
  try {
    File dir=new File(CACHE_DIR);
    for (    File f : dir.listFiles()) {
      if (f.isFile()) {
        f.delete();
      }
    }
    return true;
  }
 catch (  Exception x) {
    severe("Failed to clear disk cache: " + x);
    return false;
  }
}
