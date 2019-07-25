/** 
 * Appends the given  {@link String} to the current {@link Path}.
 * @param path
 * @return
 */
public Path slash(String path){
  return new Path(this.path + cleanUp(path),false);
}
