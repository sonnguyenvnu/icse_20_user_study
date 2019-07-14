public static void visitElements(Context ctx,XAnnotatedList xam,Node base,String name,NodeVisitor visitor,Collection<Object> result){
  Node p=base.getFirstChild();
  while (p != null) {
    if (p.getNodeType() == Node.ELEMENT_NODE) {
      if (name.equals(p.getNodeName())) {
        visitor.visitNode(ctx,xam,p,result);
      }
    }
    p=p.getNextSibling();
  }
}
