/** 
 * Adds the specified absolute paths to the module search path.
 */
public void addPaths(List<String> p) throws IOException {
  for (  String s : p) {
    addPath(s);
  }
}
