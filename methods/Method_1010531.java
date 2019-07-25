@Override public CompositeClassPathItem optimize(){
  List<RealClassPathItem> flattenedItems=flatten();
  Iterator<RealClassPathItem> it=flattenedItems.iterator();
  Set<String> alreadyVisited=new HashSet<>();
  while (it.hasNext()) {
    IClassPathItem item=it.next();
    if (item instanceof FileClassPathItem) {
      FileClassPathItem fcp=(FileClassPathItem)item;
      if (alreadyVisited.contains(fcp.getPath())) {
        it.remove();
      }
 else {
        alreadyVisited.add(fcp.getPath());
      }
    }
    if (item instanceof JarFileClassPathItem) {
      JarFileClassPathItem jfcp=(JarFileClassPathItem)item;
      String path=jfcp.getFile().getAbsolutePath();
      if (alreadyVisited.contains(path)) {
        it.remove();
      }
 else {
        alreadyVisited.add(path);
      }
    }
  }
  CompositeClassPathItem result=new CompositeClassPathItem();
  for (  IClassPathItem item : flattenedItems) {
    result.add(item);
  }
  return result;
}
