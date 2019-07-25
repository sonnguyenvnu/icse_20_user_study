protected String shrink(String absolutePath,IFile anchorFile){
  new PathAssert(absolutePath).osIndependentPath().noDots().absolute();
  String fileName;
  Set<String> macroNames=myComponent.getNames();
  for (  String macro : macroNames) {
    String path=myComponent.getValue(macro);
    if (path != null) {
      path=FileUtil.normalize(path);
      if (pathStartsWith(absolutePath,path)) {
        String relationalPath=shrink(absolutePath,path);
        fileName="${" + macro + "}" + relationalPath;
        return fileName;
      }
    }
  }
  fileName=absolutePath;
  return fileName;
}
