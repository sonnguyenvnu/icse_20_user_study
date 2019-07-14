/** 
 * Parse a file or string and return its module parse tree.
 * @param file the filename
 * @param contents optional file contents.  If {@code null}, loads the file contents from disk.
 */
@SuppressWarnings("unchecked") private ModuleType parseAndResolve(String file,String contents) throws Exception {
  ModuleType cached=(ModuleType)moduleTable.lookupType(file);
  if (cached != null) {
    return cached;
  }
  try {
    Module ast;
    if (contents != null) {
      ast=getAstForFile(file,contents);
    }
 else {
      ast=getAstForFile(file);
    }
    if (ast == null) {
      return null;
    }
 else {
      finer("resolving: " + file);
      ModuleType mod=(ModuleType)ast.resolve(moduleTable,0);
      finer("[success]");
      loadedFiles++;
      return mod;
    }
  }
 catch (  OutOfMemoryError e) {
    if (astCache != null) {
      astCache.clear();
    }
    System.gc();
    return null;
  }
}
