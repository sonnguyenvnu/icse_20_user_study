private Id elementToId(Element element,Class<? extends Annotation> annotation,int value){
  JCTree tree=(JCTree)trees.getTree(element,getMirror(element,annotation));
  if (tree != null) {
    rScanner.reset();
    tree.accept(rScanner);
    if (!rScanner.resourceIds.isEmpty()) {
      return rScanner.resourceIds.values().iterator().next();
    }
  }
  return new Id(value);
}
