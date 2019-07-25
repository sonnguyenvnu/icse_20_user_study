private static boolean intersects(Set<String> existingRoots,String parent){
  for (  String root : existingRoots) {
    if (root.equals(parent) || root.startsWith(parent + File.separator))     return true;
  }
  return false;
}
