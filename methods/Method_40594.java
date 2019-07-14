@Nullable private Type parseAndResolve(String file){
  try {
    Node ast=getAstForFile(file);
    if (ast == null) {
      failedToParse.add(file);
      return null;
    }
 else {
      Type type=Node.transformExpr(ast,globaltable);
      if (!loadedFiles.contains(file)) {
        loadedFiles.add(file);
        loadingProgress.tick();
      }
      return type;
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
