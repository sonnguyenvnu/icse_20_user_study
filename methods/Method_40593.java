@Nullable public Type loadFile(String path){
  path=_.unifyPath(path);
  File f=new File(path);
  if (!f.canRead()) {
    return null;
  }
  if (Analyzer.self.inImportStack(path)) {
    return null;
  }
  String oldcwd=cwd;
  setCWD(f.getParent());
  Analyzer.self.pushImportStack(path);
  Type type=parseAndResolve(path);
  setCWD(oldcwd);
  Analyzer.self.popImportStack(path);
  return type;
}
