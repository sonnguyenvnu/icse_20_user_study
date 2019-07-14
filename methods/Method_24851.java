private static List<String> buildCodeFolderClassPath(Sketch sketch){
  StringBuilder classPath=new StringBuilder();
  if (sketch.hasCodeFolder()) {
    File codeFolder=sketch.getCodeFolder();
    String codeFolderClassPath=Util.contentsToClassPath(codeFolder);
    classPath.append(codeFolderClassPath);
  }
  return sanitizeClassPath(classPath.toString());
}
