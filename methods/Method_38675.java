/** 
 * Maps a class to given path. For arrays, append <code>values</code> to the path to specify component type (if not specified by generics).
 */
public JsonParser map(final String path,final Class target){
  if (path == null) {
    rootType=target;
    return this;
  }
  if (mappings == null) {
    mappings=new HashMap<>();
  }
  mappings.put(Path.parse(path),target);
  return this;
}
