@Nullable private String toURL(@NotNull Binding binding,String filename){
  String destPath=binding.file;
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
