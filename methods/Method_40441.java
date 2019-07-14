/** 
 * Generate a URL for a reference to a binding.
 * @param nb the referenced binding
 * @param path the path containing the reference, or null if there was an error
 */
private String toURL(Binding nb,String path){
  Def def=nb.getSignatureNode();
  if (def == null) {
    return null;
  }
  if (nb.isBuiltin()) {
    return def.getURL();
  }
  if (nb.getKind() == Binding.Kind.MODULE) {
    return toModuleUrl(nb);
  }
  String anchor="#" + nb.getQname();
  if (nb.getFirstFile().equals(path)) {
    return anchor;
  }
  String destPath=def.getFile();
  String relpath;
  if (destPath.length() >= rootPath.length()) {
    relpath=destPath.substring(rootPath.length());
    return Util.joinPath(outDir.getAbsolutePath(),relpath) + ".html" + anchor;
  }
 else {
    System.err.println("dest path length is shorter than root path:  dest=" + destPath + ", root=" + rootPath);
    return null;
  }
}
