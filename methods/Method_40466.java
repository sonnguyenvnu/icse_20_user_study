/** 
 * Returns the module search path -- the project directory followed by any paths that were added by  {@link addPath}.
 */
public List<String> getLoadPath(){
  List<String> loadPath=new ArrayList<String>();
  if (projDir != null) {
    loadPath.add(projDir);
  }
  loadPath.addAll(path);
  return loadPath;
}
