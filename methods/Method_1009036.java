@Override protected List<Object> apply(Object child,Object parent,List siblings){
  for (  final Entry<Class,List<TraversalUtilVisitor>> entrySet : visitorMap.entrySet()) {
    final Class currentClass=entrySet.getKey();
    if (currentClass.isAssignableFrom(child.getClass())) {
      List<TraversalUtilVisitor> classVisitorList=entrySet.getValue();
      if (null != classVisitorList) {
        for (        TraversalUtilVisitor visitor : classVisitorList) {
          visitor.apply(child,parent,siblings);
        }
      }
    }
  }
  return null;
}
