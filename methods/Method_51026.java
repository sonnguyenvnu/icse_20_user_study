private static <T>void findDescendantsOfType(final Node node,final Class<T> targetType,final List<T> results,final boolean crossFindBoundaries){
  for (int i=0; i < node.jjtGetNumChildren(); i++) {
    final Node child=node.jjtGetChild(i);
    if (targetType.isAssignableFrom(child.getClass())) {
      results.add(targetType.cast(child));
    }
    if (crossFindBoundaries || !child.isFindBoundary()) {
      findDescendantsOfType(child,targetType,results,crossFindBoundaries);
    }
  }
}
