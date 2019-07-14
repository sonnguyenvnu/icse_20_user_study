@Nullable private Type parseAndResolve(String file){
  loadingProgress.tick();
  try {
    Node ast=getAstForFile(file);
    if (ast == null) {
      failedToParse.add(file);
      return null;
    }
 else {
      Type type=Node.transformExpr(ast,moduleTable);
      loadedFiles.add(file);
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
