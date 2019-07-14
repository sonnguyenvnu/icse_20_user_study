private static List<ImportStatement> buildCodeFolderImports(Sketch sketch){
  if (sketch.hasCodeFolder()) {
    File codeFolder=sketch.getCodeFolder();
    String codeFolderClassPath=Util.contentsToClassPath(codeFolder);
    StringList codeFolderPackages=Util.packageListFromClassPath(codeFolderClassPath);
    return StreamSupport.stream(codeFolderPackages.spliterator(),false).map(ImportStatement::wholePackage).collect(Collectors.toList());
  }
  return Collections.emptyList();
}
