/** 
 * Loads a module from a string containing the module contents. Idempotent:  looks in the module cache first. Used for simple unit tests.
 * @param path a path for reporting/caching purposes.  The filenamecomponent is used to construct the module qualified name.
 */
public ModuleType loadString(String path,String contents) throws Exception {
  ModuleType module=getCachedModule(path);
  if (module != null) {
    finer("\nusing cached module " + path + " [succeeded]");
    return module;
  }
  return parseAndResolve(path,contents);
}
