/** 
 * Generate an anchorless URL linking to another file in the index.
 */
private String toModuleUrl(Binding nb){
  ModuleType mtype=nb.getType().asModuleType();
  String path=mtype.getFile();
  if (!path.startsWith(rootPath)) {
    return "file://" + path;
  }
  String relpath=path.substring(rootPath.length());
  return Util.joinPath(outDir.getAbsolutePath(),relpath) + ".html";
}
