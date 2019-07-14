private Map<Integer,Id> elementToIds(Element element,Class<? extends Annotation> annotation,int[] values){
  Map<Integer,Id> resourceIds=new LinkedHashMap<>();
  JCTree tree=(JCTree)trees.getTree(element,getMirror(element,annotation));
  if (tree != null) {
    rScanner.reset();
    tree.accept(rScanner);
    resourceIds=rScanner.resourceIds;
  }
  for (  int value : values) {
    resourceIds.putIfAbsent(value,new Id(value));
  }
  return resourceIds;
}
