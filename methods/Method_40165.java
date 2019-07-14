@Nullable private String toURL(@NotNull Binding binding,String filename){
  if (binding.isBuiltin()) {
    return binding.getURL();
  }
  String destPath;
  if (binding.type instanceof ModuleType) {
    destPath=binding.type.asModuleType().file;
  }
 else {
    destPath=binding.getFile();
  }
  if (destPath == null) {
    return null;
  }
  String anchor="#" + binding.qname;
  if (binding.getFirstFile().equals(filename)) {
    return anchor;
  }
  if (destPath.startsWith(rootPath)) {
    String relpath;
    if (filename != null) {
      relpath=_.relPath(filename,destPath);
    }
 else {
      relpath=destPath;
    }
    if (relpath != null) {
      return relpath + ".html" + anchor;
    }
 else {
      return anchor;
    }
  }
 else {
    return "file://" + destPath + anchor;
  }
}
