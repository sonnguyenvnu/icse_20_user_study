public static void visitAttributes(Context ctx,XAnnotatedList xam,Node base,String name,String attrName,NodeVisitor visitor,Collection<Object> result){
  Node p=base.getFirstChild();
  while (p != null) {
    if (p.getNodeType() == Node.ELEMENT_NODE) {
      if (name.equals(p.getNodeName())) {
        Node at=p.getAttributes().getNamedItem(attrName);
        if (at != null) {
          visitor.visitNode(ctx,xam,at,result);
        }
      }
    }
    p=p.getNextSibling();
  }
}
