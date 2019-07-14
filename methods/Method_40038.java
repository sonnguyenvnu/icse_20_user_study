@Nullable public Type loadFile(String path){
  path=_.unifyPath(path);
  File f=new File(path);
  if (!f.canRead()) {
    return null;
  }
  Type module=getCachedModule(path);
  if (module != null) {
    return module;
  }
  if (Analyzer.self.inImportStack(path)) {
    return null;
  }
  String oldcwd=cwd;
  setCWD(f.getParent());
  Analyzer.self.pushImportStack(path);
  Type type=parseAndResolve(path);
  Analyzer.self.popImportStack(path);
  setCWD(oldcwd);
  return type;
}
