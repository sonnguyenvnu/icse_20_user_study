/** 
 * Returns the relative path between root and dest, or null if dest is not a child of root.
 */
static @Nullable String relativize(File root,File dest){
  String rootPath=root.getAbsolutePath();
  String destPath=dest.getAbsolutePath();
  if (!destPath.startsWith(rootPath)) {
    return null;
  }
 else {
    return destPath.substring(rootPath.length());
  }
}
