/** 
 * Returns the syntax tree for  {@code path} with {@code contents}. Uses the memory cache but not the disk cache. This method exists primarily for unit testing.
 * @param path a name for the file.  Can be relative.
 * @param contents the source to parse
 */
public Module getAST(String path,String contents) throws Exception {
  if (path == null)   throw new IllegalArgumentException("null path");
  if (contents == null)   throw new IllegalArgumentException("null contents");
  if (cache.containsKey(path)) {
    return cache.get(path);
  }
  Module mod=null;
  try {
    mod=parse(path,contents);
    if (mod != null) {
      mod.setFileAndMD5(path,Util.getMD5(contents.getBytes("UTF-8")));
    }
  }
  finally {
    cache.put(path,mod);
  }
  return mod;
}
