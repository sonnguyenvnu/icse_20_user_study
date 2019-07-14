protected void check(Node node){
  if (imports.isEmpty()) {
    return;
  }
  ImportWrapper candidate=getImportWrapper(node);
  Iterator<ImportWrapper> it=imports.iterator();
  while (it.hasNext()) {
    ImportWrapper i=it.next();
    if (i.matches(candidate)) {
      it.remove();
      return;
    }
  }
  if (TypeNode.class.isAssignableFrom(node.getClass()) && ((TypeNode)node).getType() != null) {
    Class<?> c=((TypeNode)node).getType();
    if (c.getPackage() != null) {
      candidate=new ImportWrapper(c.getPackage().getName(),null);
      if (imports.contains(candidate)) {
        imports.remove(candidate);
      }
    }
  }
}
