/** 
 * Sets the module search path to the specified list of absolute paths.
 */
public void setPath(List<String> path) throws IOException {
  this.path=new ArrayList<String>(path.size());
  addPaths(path);
}
