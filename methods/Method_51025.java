@Override public <T>List<T> getParentsOfType(final Class<T> parentType){
  final List<T> parents=new ArrayList<>();
  Node parentNode=jjtGetParent();
  while (parentNode != null) {
    if (parentType.isInstance(parentNode)) {
      parents.add(parentType.cast(parentNode));
    }
    parentNode=parentNode.jjtGetParent();
  }
  return parents;
}
