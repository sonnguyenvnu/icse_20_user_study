public static void visitMapAttributes(Context ctx,XAnnotatedMap xam,Node base,String name,String attrName,NodeMapVisitor visitor,Map<String,Object> result){
  Node p=base.getFirstChild();
  while (p != null) {
    if (p.getNodeType() == Node.ELEMENT_NODE) {
      if (name.equals(p.getNodeName())) {
        Node at=p.getAttributes().getNamedItem(attrName);
        if (at != null) {
          String key=getNodeValue((Element)p,xam.key);
          if (key != null) {
            visitor.visitNode(ctx,xam,at,key,result);
          }
        }
      }
    }
    p=p.getNextSibling();
  }
}
