@Override public <T>List<T> findChildrenOfType(final Class<T> targetType){
  final List<T> list=new ArrayList<>();
  for (int i=0; i < jjtGetNumChildren(); i++) {
    final Node child=jjtGetChild(i);
    if (targetType.isInstance(child)) {
      list.add(targetType.cast(child));
    }
  }
  return list;
}
