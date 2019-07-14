private static final String updatePrefix(Section root,String prefix){
  if (root != null && root.getParent() == null) {
    return root.getClass().getSimpleName();
  }
 else   if (root != null) {
    return prefix + "->" + root.getClass().getSimpleName();
  }
  return "";
}
