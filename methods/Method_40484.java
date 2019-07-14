/** 
 * Load, parse and analyze a source file given its absolute path. By default, loads the entire ancestor package chain.
 * @param path the absolute path to the file or directory.If it is a directory, it is suffixed with "__init__.py", and only that file is loaded from the directory.
 * @param noparents {@code true} to skip loading ancestor packages
 * @return {@code null} if {@code path} could not be loaded
 */
public ModuleType loadFile(String path,boolean skipChain) throws Exception {
  File f=new File(path);
  if (f.isDirectory()) {
    finer("\n    loading init file from directory: " + path);
    f=Util.joinPath(path,"__init__.py");
    path=f.getAbsolutePath();
  }
  if (!f.canRead()) {
    finer("\nfile not not found or cannot be read: " + path);
    return null;
  }
  ModuleType module=getCachedModule(path);
  if (module != null) {
    finer("\nusing cached module " + path + " [succeeded]");
    return module;
  }
  if (!skipChain) {
    loadParentPackage(path);
  }
  try {
    return parseAndResolve(path);
  }
 catch (  StackOverflowError soe) {
    handleException("Error loading " + path,soe);
    return null;
  }
}
